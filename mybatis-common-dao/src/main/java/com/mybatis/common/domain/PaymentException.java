package com.mybatis.common.domain;

/**
 * @ description:
 * @ author: guojiang.xiong
 * @ created: 2017-05-19 下午5:36
 */
public class PaymentException extends RuntimeException {
    private String code;
    private String msg;
    private Throwable e;

    public PaymentException(String msg) {
        super(msg);
        this.code = BaseResponseCode.FAILURE.getCode();
        this.msg = msg;
    }

    public PaymentException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public PaymentException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.e = e;
    }

    public PaymentException(String code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
        this.msg = msg;
        this.e = e;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public Throwable getE() {
        return this.e;
    }
}

