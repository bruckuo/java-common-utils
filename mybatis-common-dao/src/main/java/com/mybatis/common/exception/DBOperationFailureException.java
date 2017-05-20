package com.mybatis.common.exception;

/**
 * @ description:
 * @ author: guojiang.xiong
 * @ created: 2017-05-19 下午5:40
 */
public class DBOperationFailureException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DBOperationFailureException() {
    }

    public DBOperationFailureException(String message) {
        super(message);
    }

    public DBOperationFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBOperationFailureException(Throwable cause) {
        super(cause);
    }
}