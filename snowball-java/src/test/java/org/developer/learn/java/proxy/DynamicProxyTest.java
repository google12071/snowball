package org.developer.learn.java.proxy;

import lombok.extern.slf4j.Slf4j;
import org.developer.learn.common.pojo.Person;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @ClassName JdkDynamicProxyTest
 * @Description: JDK动态代理测试
 * @Author lfq
 * @Date 2020/5/12
 **/
@Slf4j
public class DynamicProxyTest {
    @Test
    public void jdkDynamicProxy() {
        CustomerService customerService = new CustomerServiceImpl();
        ClassLoader loader = customerService.getClass().getClassLoader();
        CustomerService proxy = (CustomerService) Proxy.newProxyInstance(loader, customerService.getClass().getInterfaces(),
                new WaiterHandler(customerService));
        proxy.eating("fq");
    }

    @Test
    public void cglibDynamicProxy() {
        PersonService target = new PersonService();
        PersonService proxy = (PersonService) new PersonServiceProxy(target).getProxyInstance();
        log.info("proxy class:{}", proxy.getClass());
        Person person = new Person("张三", 25, "安徽省宿州市");
        proxy.save(person);
    }
}
