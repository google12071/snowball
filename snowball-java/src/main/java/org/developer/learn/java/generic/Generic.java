package org.developer.learn.java.generic;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Generic
 * @Description:泛型类
 * @Author lfq
 * @Date 2020/9/23
 **/
@Slf4j
public class Generic<T> {
    private T key;

    public Generic(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    /**
     * 类型通配符一般用“?”表示,可看作是所有类型的父类,是一种真实存在的类型
     *
     * @param obj
     */
    public void showKey(Generic<?> obj) {
        log.info("obj key:{}", obj.getKey());
    }

    /**
     * 泛型方法
     *
     * @param tClass
     * @param <E>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <E> E genericMethod(Class<E> tClass) throws IllegalAccessException, InstantiationException {
        return tClass.newInstance();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Generic{");
        sb.append("key=").append(key);
        sb.append('}');
        return sb.toString();
    }
}
