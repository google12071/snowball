package org.developer.learn.java.design.pattern.singleton;

/**
 * @ClassName Singleton
 * @Description:单例模式(静态内部类实现)，线程安全，实现简单（推荐使用）
 * @Author lfq
 * @Date 2020/11/2
 **/
public class Singleton {
    /**
     * 构造器私有化，防止外部通过new关键字创建对象
     */
    private Singleton() {
    }

    /**
     * 类加载时创建单例模式对象(类的静态变量初次被访问时将会触发类的初始化，其静态变量的值将会由默认值变为初始值)
     */
    public static class InnerClass {
        private static final Singleton singleton = new Singleton();
    }

    public static Singleton getInstance() {
        return InnerClass.singleton;
    }

}
