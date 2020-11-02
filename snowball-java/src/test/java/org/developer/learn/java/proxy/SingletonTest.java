package org.developer.learn.java.proxy;

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
            System.out.println("true");
        }
    }
}
