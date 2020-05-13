package org.developer.learn.common.reflection;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @ClassName ReflectionTest
 * @Description: 反射机制测试类
 * @Author lfq
 * @Date 2020/5/13
 **/
@Slf4j
public class ReflectionTest {
    @Test
    public void displayClass(){
        String className = "org.developer.learn.common.pojo.Employee";
        ClassInfoDisplay.display(className);
    }
}
