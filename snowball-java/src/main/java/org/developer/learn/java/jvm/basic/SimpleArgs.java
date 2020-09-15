package org.developer.learn.java.jvm.basic;

/**
 * @ClassName SimpleArgs
 * @Description:打印Java虚拟机参数
 * @Author lfq
 * @Date 2020/9/15
 **/
public class SimpleArgs {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println("参数" + (i + 1) + ":" + args[i]);
        }
        System.out.println("-Xmx" + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "M");
    }
}
