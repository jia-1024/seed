package com.company.project.core;

/**
 * 项目级别异常基类
 *
 * @author JHL
 * @version 1.0
 * @date 2023/4/21 15:40
 * @since : JDK 11
 */
public class BaseException extends RuntimeException {

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

}