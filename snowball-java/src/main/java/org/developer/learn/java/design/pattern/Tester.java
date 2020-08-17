package org.developer.learn.java.design.pattern;

/**
 * @ClassName Tester
 * @Description:
 * @Author lfq
 * @Date 2020/8/17
 **/
public class Tester {
    public static void main(String[] args) {
        AbstractDisplay d1 = new CharDisplay('H');
        AbstractDisplay d2 = new StringDisplay("hello world");
        AbstractDisplay d3 = new StringDisplay("这是个美丽的世界");

        d1.display();
        d2.display();
        d3.display();
    }
}
