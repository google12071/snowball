package org.developer.learn.java.concurrent.basic;

/**
 * @ClassName SimpleTimer
 * @Description:sleep方法实现的简易倒计时器材
 * @Author lfq
 * @Date 2020/8/9
 **/
public class SimpleTimer {
    private static int count;

    public static void main(String[] args) {
        count = args.length > 0 ? Integer.parseInt(args[0]) : 60;
        int remaining;
        while (true) {
            remaining = countDown();
            if (remaining <= 0) {
                System.out.println("倒计时结束");
                break;
            } else {
                System.out.println("倒计时:" + count);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static int countDown() {
        return count--;
    }
}
