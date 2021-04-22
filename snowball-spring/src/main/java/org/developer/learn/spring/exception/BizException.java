package org.developer.learn.spring.exception;

/**
 * @ClassName BizException
 * @Description:
 * @Author lfq
 * @Date 2021/4/22
 **/
public class BizException extends ServiceError {
    private String message;

    public BizException(String code, String message) {
        super(code, message);
        this.message = message;
    }

    public BizException(String message) {
        super(null, message);
        this.message = message;
    }
}
