package org.developer.learn.java.jvm.basic;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName CommonClass
 * @Description:
 * @Author lfq
 * @Date 2020/9/22
 **/
@Slf4j
public class CommonClass {
    private static final int num = 58;
    private static Integer age = 20;
    private String address;

    public void method() {
        log.info("common method invoke");
    }

    public static void staticMethod() {
        log.error("static method invoke");
        age = 50;
    }


    public static void main(String[] args) {
        System.out.println("age:" + CommonClass.age);
        CommonClass.staticMethod();
        new CommonClass().method();
    }
}
