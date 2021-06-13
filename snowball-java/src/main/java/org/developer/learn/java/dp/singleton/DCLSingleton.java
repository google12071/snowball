package org.developer.learn.java.dp.singleton;

/**
 * @ClassName DCLSingleton（Double Check Lock）
 * @Description:基于双重检查加锁（线程安全）
 * @Author lfq
 * @Date 2020/11/2
 **/
public class DCLSingleton {
    /**
     * 注意：此处必须使用volatile关键字修饰，否则可能会因为编译器的指令重排序，导致线程安全性问题
     */
    private static volatile DCLSingleton instance;

    /**
     * 构造器私有化
     */
    private DCLSingleton() {

    }

    /**
     * 双重检查加锁
     *
     * @return
     */
    public static DCLSingleton getInstance() {
        //第1次检查
        if (instance == null) {
            synchronized (DCLSingleton.class) {
                //第2次检查
                if (instance == null) {
                    instance = new DCLSingleton();
                }
                return instance;
            }
        }
        return instance;
    }
}
