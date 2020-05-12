package org.develeoper.learn.common.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName WaiterHandler
 * @Description: JDK实现动态代理
 * @Author lfq
 * @Date 2020/5/11
 **/
@Slf4j
public class WaiterHandler implements InvocationHandler {
    /**
     * 目标代理对象
     */
    private Object target;

    public WaiterHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (args.length == 0) {
            throw new RuntimeException("参数异常");
        }
        String name = (String) args[0];
        this.greeting(name);
        Object obj = method.invoke(target, args);
        this.enjoy();

        return obj;
    }

    private void greeting(String name) {
        System.out.println("hello," + name);
    }

    private void enjoy() {
        System.out.println("enjoy yourself");
    }
}
