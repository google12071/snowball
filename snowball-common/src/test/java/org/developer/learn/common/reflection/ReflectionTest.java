package org.developer.learn.common.reflection;

import lombok.extern.slf4j.Slf4j;
import org.developer.learn.common.pojo.Employee;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @ClassName ReflectionTest
 * @Description: 反射机制测试类
 * @Author lfq
 * @Date 2020/5/13
 **/
@Slf4j
public class ReflectionTest {
    @Test
    public void displayClass() {
        String className = "org.developer.learn.common.pojo.Employee";
        ClassInfoDisplay.display(className);
    }

    @Test
    public void methodInvoke() {
        try {
            Employee employee = new Employee("张三", 23, "浙江杭州", 35, "Java开发工程师", 15000);
            Method method = employee.getClass().getMethod("square", Double.class);
            Double value = (Double) method.invoke(employee, 3.3);
            log.info("value={}", value);

            Method sport = Employee.class.getMethod("sport", String.class);
            sport.invoke(employee, "basketball");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void accessModify() throws IllegalAccessException {
        Employee employee = new Employee("张三", 23, "浙江杭州", 35, "Java开发工程师", 15000);
        Field[] fields = employee.getClass().getFields();
        for (Field field : fields) {
            String name = field.getName();
            String modifier = Modifier.toString(field.getModifiers());
            boolean isAccessible = field.isAccessible();
            if (!isAccessible) {
                field.setAccessible(true);
            }
        }
    }
}
