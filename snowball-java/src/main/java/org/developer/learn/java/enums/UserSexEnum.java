package org.developer.learn.java.enums;

/**
 * 用户性别枚举
 * @author lfq
 */

public enum UserSexEnum {
    MAN("MAN", "男人"),
    WOMAN("WOMAN", "女人");

    UserSexEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
