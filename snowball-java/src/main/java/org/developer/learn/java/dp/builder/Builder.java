package org.developer.learn.java.dp.builder;

/**
 * @ClassName Builder
 * @Description:
 * @Author lfq
 * @Date 2020/10/7
 **/
public abstract class Builder {
    public abstract void makeTitle(String title);

    public abstract void makeString(String str);

    public abstract void makeItems(String[] items);

    public abstract void close();
}
