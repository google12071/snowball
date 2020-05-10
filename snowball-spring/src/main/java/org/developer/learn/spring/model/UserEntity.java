package org.developer.learn.spring.model;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName UserEntity
 * @Description:
 * @Author lfq
 * @Date 2020/5/10
 **/
@Data
public class UserEntity {
    private Long uid;
    private String name;
    private Integer age;
    private String address;
    private String des;
    private Double money;
    private Date createTime;
    private Date updateTime;
}
