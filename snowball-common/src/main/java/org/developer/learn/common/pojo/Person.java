package org.developer.learn.common.pojo;

import lombok.Data;

/**
 * @ClassName Person
 * @Description:
 * @Author lfq
 * @Date 2020/5/13
 **/
@Data
public class Person {
    public String name;
    protected Integer age;
    private String address;

    public Person(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public Person() {
    }
}
