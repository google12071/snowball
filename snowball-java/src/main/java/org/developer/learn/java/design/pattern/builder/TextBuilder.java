package org.developer.learn.java.design.pattern.builder;

import org.developer.learn.java.design.pattern.builder.Builder;

/**
 * @ClassName TextBuilder
 * @Description:
 * @Author lfq
 * @Date 2020/10/7
 **/
public class TextBuilder extends Builder {

    @Override
    public void makeTitle(String title) {
        System.out.println("Text:" + title);
    }

    @Override
    public void makeString(String str) {
        System.out.println("Text:" + str);
    }

    @Override
    public void makeItems(String[] items) {
        if (items.length > 0) {
            System.out.println("Text length:" + items.length);
        }
    }

    @Override
    public void close() {
        System.out.println("Text close");
    }
}
