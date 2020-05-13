package org.developer.learn.common.reflection;

import lombok.extern.slf4j.Slf4j;
import org.developer.learn.common.pojo.Employee;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * @ClassName ClassTest
 * @Description: 三种方式获取Employee对象
 * 1、Employee.class
 * 2、Class cc=Class.forName()
 * 3. employee.getClass()
 * @Author lfq
 * @Date 2020/5/13
 **/
@Slf4j
public class ClassTest {
    @Test
    public void buildClass() {
        try {
            Class c1 = Class.forName("org.developer.learn.common.pojo.Employee");
            Class c2 = Employee.class;
            Employee employee = new Employee("张三", 23, "浙江杭州", 35, "Java开发工程师", 15000);
            Class c3 = employee.getClass();

            if (c1 == c2 && c2 == c3) {
                System.out.println("employee class:" + employee.getClass().getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void newInstance() {
        try {
            Class c = Date.class;
            Date date = (Date) c.newInstance();
            log.info("date," + date);

            long timestamp = System.currentTimeMillis();
            Constructor<Date> dateConstructor = c.getConstructor(long.class);
            Date date2 = dateConstructor.newInstance(timestamp);
            log.info("date2," + date2);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
