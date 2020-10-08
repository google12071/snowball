package org.developer.learn.java.design.pattern;

/**
 * @author lfq
 */
public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void builder() {
        builder.makeTitle("this is title");
        builder.makeString("this is String");
        builder.makeItems(new String[]{"hello", "everyone"});
        builder.makeString("welcome to java world");
        builder.makeItems(new String[]{"welcome", "java"});
        builder.close();
    }
}
