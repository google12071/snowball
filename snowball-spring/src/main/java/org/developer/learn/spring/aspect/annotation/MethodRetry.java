package org.developer.learn.spring.aspect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 失败重试策略配置注解
 *
 * @author lfq
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodRetry {
    /**
     * 失败重试次数，默认3次
     *
     * @return
     */
    int retryTimes() default 3;

    /**
     * 失败延迟重试，默认200毫秒
     *
     * @return
     */
    int backoff() default 100;

    /**
     * 失败重试异常类型
     *
     * @return
     */
    Class<? extends Throwable> retryOnException() default Exception.class;
}
