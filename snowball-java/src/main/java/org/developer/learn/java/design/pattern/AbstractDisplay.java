package org.developer.learn.java.design.pattern;

/**
 * @ClassName AbstractDisplay
 * @Description: 公共基类（抽象父类）
 * @Author lfq
 * @Date 2020/8/17
 **/
public abstract class AbstractDisplay {
    public abstract void open();

    public abstract void print();

    public abstract void close();

    public final void display() {
        open();
        for (int i = 0; i < 5; i++) {
            print();
        }
        close();
    }
}
