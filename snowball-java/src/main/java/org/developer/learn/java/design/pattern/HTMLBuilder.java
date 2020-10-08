package org.developer.learn.java.design.pattern;

/**
 * @ClassName HTMLBuilder
 * @Description:
 * @Author lfq
 * @Date 2020/10/7
 **/
public class HTMLBuilder extends Builder {

    @Override
    public void makeTitle(String title) {
        System.out.println("html:" + title);
    }

    @Override
    public void makeString(String str) {
        System.out.println("html:" + str);
    }

    @Override
    public void makeItems(String[] items) {
        for (String s : items) {
            System.out.println(s);
        }
    }

    @Override
    public void close() {
        System.out.println("html close");
    }
}
