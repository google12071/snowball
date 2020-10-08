package org.developer.learn.java.generic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GenericTest
 * @Description:
 * @Author lfq
 * @Date 2020/9/23
 **/
@Slf4j
public class GenericTest {
    @Test
    public void typeErasure() {
        List<String> stringList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        log.info("classType1:{},classType2:{}", stringList.getClass(), integerList.getClass());
    }

    @Test
    public void genericOperate() {
        Generic<Integer> integerGeneric = new Generic<>(10);
        Generic<String> stringGeneric = new Generic<>("hello");
        Generic<Boolean> booleanGeneric = new Generic<>(true);
        log.info("classType:{}", integerGeneric.getClass());
        log.info("value1:{},value2:{},value3:{}", integerGeneric, stringGeneric, booleanGeneric);

        integerGeneric.showKey(new Generic<>("test"));
    }

    @Test
    public void genericMethod() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Generic<Integer> integerGeneric = new Generic<>(10);
        Object obj1 = integerGeneric.genericMethod(Class.forName("org.developer.learn.java.jvm.basic.CommonClass"));
        log.info("obj1:{}", obj1.getClass());
    }
}
