package org.developer.learn.java.proxy;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName PersonServiceProxy
 * @Description:Cglib动态代理 1、目标类无需实现接口，通过运行时字节码技术动态创建目标类子类
 * 2、代理类通过实现MethodInterceptor实现方法拦截
 * 3、Cglib生成代理基于继承实现，因此目标对象不能是final类型
 * @Author lfq
 * @Date 2020/5/13
 **/
@Slf4j
public class PersonServiceProxy implements MethodInterceptor {

    /**
     * 目标对象
     */
    private Object target;

    public PersonServiceProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("before method intercept");
        //注意此处是invokeSuper，否则会死循环，method.invoke执行的是子类的方法
        Object returnVal = methodProxy.invokeSuper(proxy, args);
        log.info("after method intercept");
        return returnVal;
    }

    /**
     * 为目标类生成代理对象
     *
     * @return
     */
    public Object getProxyInstance() {
        Enhancer enhancer = new Enhancer();
        //设置目标类为代理类的父类
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }
}
