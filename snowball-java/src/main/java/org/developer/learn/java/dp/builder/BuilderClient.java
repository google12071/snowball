package org.developer.learn.java.dp.builder;

/**
 * @ClassName BuilderClient
 * @Description:建造者模式测试类
 * @Author lfq
 * @Date 2020/10/7
 **/
public class BuilderClient {
    public static void main(String[] args) {
        Director htmlDirector = new Director(new HTMLBuilder());
        Director textDirector = new Director(new TextBuilder());
        htmlDirector.builder();
        textDirector.builder();
    }
}
