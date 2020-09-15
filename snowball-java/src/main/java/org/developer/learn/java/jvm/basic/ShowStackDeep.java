package org.developer.learn.java.jvm.basic;

/**
 * @ClassName ShowStackDeep
 * @Description:打印调用栈深度,递归调用时Java栈会创建大量栈桢导致栈溢出
 * @Author lfq
 * @Date 2020/9/15
 **/
public class ShowStackDeep {
    private static int count = 0;

    public static void recursion() {
        count++;
        recursion();
    }

    public static void main(String[] args) {
        try {
            recursion();
        } catch (Throwable e) {
            System.out.println("deep of calling:" + count);
            e.printStackTrace();
        }
    }
}
