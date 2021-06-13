package org.developer.learn.java.dp.templateMethod;

/**
 * @ClassName CharDisplay
 * @Description:
 * @Author lfq
 * @Date 2020/8/17
 **/
public class CharDisplay extends AbstractDisplay {
    private char ch;

    public CharDisplay(char ch) {
        this.ch = ch;
    }

    @Override
    public void open() {
        System.out.println("<<");
    }

    @Override
    public void print() {
        System.out.println(ch);
    }

    @Override
    public void close() {
        System.out.println(">>");
    }
}
