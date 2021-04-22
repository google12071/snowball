package org.developer.learn.spring.exception;

/**
 * @ClassName ServiceError
 * @Description:
 * @Author lfq
 * @Date 2021/4/22
 **/
public class ServiceError extends RuntimeException {
    private String code;
    private String message;

    public ServiceError(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
