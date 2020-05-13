package org.developer.learn.java.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;

/**
 * @ClassName JdkDynamicProxyTest
 * @Description: JDK动态代理测试
 * @Author lfq
 * @Date 2020/5/12
 **/
@Slf4j
public class JdkDynamicProxyTest {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerServiceImpl();
        ClassLoader loader = customerService.getClass().getClassLoader();
        CustomerService proxy = (CustomerService) Proxy.newProxyInstance(loader, customerService.getClass().getInterfaces(),
                new WaiterHandler(customerService));
        proxy.eating("fq");
    }
}
