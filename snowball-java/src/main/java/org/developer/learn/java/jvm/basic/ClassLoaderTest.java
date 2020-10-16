package org.developer.learn.java.jvm.basic;

/**
 * @ClassName ClassLoaderTest
 * @Description:类加载机制测试
 * @Author lfq
 * @Date 2020/10/16
 **/
public class ClassLoaderTest {
    public static void main(String[] args) {
        ClassLoader loader = ClassStructure.class.getClassLoader();
        System.out.println(loader);
    }
}
