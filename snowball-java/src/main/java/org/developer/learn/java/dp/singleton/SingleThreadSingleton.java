package org.developer.learn.java.dp.singleton;

/**
 * @ClassName SingleThreadSingleton
 * @Description: 单线程环境实现的单例模式
 * @Author lfq
 * @Date 2020/11/2
 **/
public class SingleThreadSingleton {
    private static SingleThreadSingleton singleton;

    /**
     * 构造器私有化
     */
    private SingleThreadSingleton() {
    }

    /**
     * 获取单例实例
     *
     * @return
     */
    public static SingleThreadSingleton getInstance() {
        if (singleton == null) {
            singleton = new SingleThreadSingleton();
        }
        return singleton;
    }
}
