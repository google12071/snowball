package org.developer.learn.java.proxy;

import org.developer.learn.java.design.pattern.singleton.DCLSingleton;
import org.developer.learn.java.design.pattern.singleton.SingleThreadSingleton;
import org.developer.learn.java.design.pattern.singleton.Singleton;
import org.junit.Test;

/**
 * @ClassName SingletonTest
 * @Description:
 * @Author lfq
 * @Date 2020/11/2
 **/
public class SingletonTest {
    @Test
    public void getInstanceByInnerClass() {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        Singleton s3 = Singleton.getInstance();
        Singleton s4 = Singleton.getInstance();
        if (s1 == s2 && s2 == s3 && s3 == s4) {
            System.out.println("true,obj:" + s1);
        }
    }

    @Test
    public void getInstanceByDCL() {
        DCLSingleton d1 = DCLSingleton.getInstance();
        DCLSingleton d2 = DCLSingleton.getInstance();
        DCLSingleton d3 = DCLSingleton.getInstance();
        DCLSingleton d4 = DCLSingleton.getInstance();
        if (d1 == d2 && d2 == d3 && d3 == d4) {
            System.out.println("true,hashcode:" + d1.hashCode());
        }
    }

    @Test
    public void getSingleThreadInstance() {
        SingleThreadSingleton s1 = SingleThreadSingleton.getInstance();
        SingleThreadSingleton s2 = SingleThreadSingleton.getInstance();
        if (s1 == s2) {
            System.out.println("true,hashcode:" + s1.hashCode());
        }
    }
}
