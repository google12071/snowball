package org.develeoper.learn.common.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName User
 * @Description:
 * @Author lfq
 * @Date 2020/5/10
 **/
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -7894175429782772955L;
    private Long uid;
    private String name;
    private Integer age;
    private String address;
    private String des;
    private Double money;

    public User(Long uid, String name, Integer age, String address, String des, Double money) {
        this.uid = uid;
        this.name = name;
        this.age = age;
        this.address = address;
        this.des = des;
        this.money = money;
    }
}
