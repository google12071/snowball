package org.developer.learn.java.generic;

import lombok.extern.slf4j.Slf4j;
import org.developer.learn.java.enums.UserSexEnum;
import org.junit.Test;

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
        Object obj1 = integerGeneric.genericMethod(Class.forName("org.developer.learn.java.jvm.classloader.CommonClass"));
        log.info("obj1:{}", obj1.getClass());
    }


    @Test
    public void userSexEnum(){
        String sex = "WOMAN";
        String result = null;
        UserSexEnum[] sexEnums = UserSexEnum.values();
        for (UserSexEnum sexEnum : sexEnums) {
            if (sex.equals(sexEnum.name())) {
                result = sexEnum.name();
            }
        }
        System.out.println(result);
    }

    public static String substring(String source, int start, int end) {
        String result;
        try {
            result = source.substring(source.offsetByCodePoints(0, start),
                    source.offsetByCodePoints(0, end));
        } catch (Exception e) {
            result = "";
        }
        return result;
    }


    public static void main(String[]args){
         String s="这是一个😄👌你我的榜样";
         s=substring(s,3,6);
         System.out.println(s);
    }
}
