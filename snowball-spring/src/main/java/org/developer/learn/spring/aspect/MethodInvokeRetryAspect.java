package org.developer.learn.spring.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.developer.learn.spring.aspect.annotation.MethodRetry;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName MethodInvokeRetryAspect
 * @Description:方法调用失败重试
 * @Author lfq
 * @Date 2021/4/22
 **/
@Aspect
@Component
@Slf4j
public class MethodInvokeRetryAspect {
    private static final Map<String, RetryTemplate> TEMPLATE_REPOSITORY = new ConcurrentHashMap<>(10);

    @Around("@annotation(org.developer.learn.spring.aspect.annotation.MethodRetry)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        try {
            //获取方法签名
            Signature signature = point.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            Method currentMethod = point.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
            MethodRetry methodRetry = currentMethod.getAnnotation(MethodRetry.class);
            if (methodRetry == null) {
                return point.proceed();
            }
            long startTime = System.currentTimeMillis();
            Object ret;
            if (methodRetry.retryTimes() > 0) {
                ret = getTemplate(methodRetry, methodRetry.retryOnException()).execute(context -> point.proceed());
            } else {
                ret = point.proceed();
            }
            long duration = System.currentTimeMillis() - startTime;
            log.info("方法调用执行时间：" + duration);
            return ret;
        } catch (Exception e) {
            log.info("方法调用异常",e);
        }
        return point.proceed();
    }

    /**
     * 获取失败重试模版
     * @param methodRetry
     * @param retryOnException
     * @return
     */
    private RetryTemplate getTemplate(MethodRetry methodRetry, Class<? extends Throwable> retryOnException) {
        String key = String.format("%d-%d-%s", methodRetry.retryTimes(), methodRetry.backoff(), retryOnException.getCanonicalName());
        if (!TEMPLATE_REPOSITORY.containsKey(key)) {
            log.info("RetryTemplate key:{}", key);
            RetryTemplate template = RetryTemplate.builder()
                    // 最大重试次数
                    .maxAttempts(Math.min(methodRetry.retryTimes(), 5))
                    // 以下异常下重试
                    .retryOn(retryOnException)
                    // 固定100ms 之后重试
                    .fixedBackoff(methodRetry.backoff())
                    .build();
            TEMPLATE_REPOSITORY.put(key, template);
            return template;
        }
        return TEMPLATE_REPOSITORY.get(key);
    }
}
