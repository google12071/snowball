package org.developer.learn.java.design.pattern.templateMethod;

import org.developer.learn.java.design.pattern.templateMethod.AbstractDisplay;

/**
 * @ClassName StringDisplay
 * @Description:
 * @Author lfq
 * @Date 2020/8/17
 **/
public class StringDisplay extends AbstractDisplay {
    private String content;
    private Integer weight;

    public StringDisplay(String content) {
        this.content = content;
        this.weight = content.length();
    }

    @Override
    public void open() {
        printLine();
    }

    @Override
    public void print() {
        System.out.print("|" + content + "|");
    }

    @Override
    public void close() {
        printLine();
    }

    private void printLine() {
        System.out.println("+");
        for (int i = 0; i < weight; i++) {
            System.out.println("-");
        }
        System.out.println("+");
    }
}
