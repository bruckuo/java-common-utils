package com.mybatis.common.domain;


import com.mybatis.common.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @ description:
 * @ author: guojiang.xiong
 * @ created: 2017-05-19 下午5:31
 */
public class BaseMsgException extends RuntimeException {
    private static final long serialVersionUID = 7398568719311520034L;
    private String module;
    private String code;
    private Object[] args;
    private String defaultMessage;

    public BaseMsgException() {
    }

    public BaseMsgException(String module, String code, Object[] args, String defaultMessage) {
        this.module = module;
        this.code = code;
        this.args = args;
        this.defaultMessage = defaultMessage;
    }

    public BaseMsgException(String module, String code, Object[] args) {
        this(module, code, args, (String) null);
    }

    public BaseMsgException(String module, String defaultMessage) {
        this(module, (String) null, (Object[]) null, defaultMessage);
    }

    public BaseMsgException(String code, Object[] args) {
        this((String) null, code, args, (String) null);
    }

    public BaseMsgException(String defaultMessage) {
        this((String) null, (String) null, (Object[]) null, defaultMessage);
    }

    public String getMessage() {
        String message = null;
        if (StringUtils.isNotEmpty(this.code)) {
            message = MessageUtils.message(this.code, this.args);
        }

        if (message == null) {
            message = this.defaultMessage;
        }

        return message;
    }

    public String getModule() {
        return this.module;
    }

    public String getCode() {
        return this.code;
    }

    public Object[] getArgs() {
        return this.args;
    }

    public String getDefaultMessage() {
        return this.defaultMessage;
    }

    public String toString() {
        return this.getClass().toString() + "{module='" + this.module + "',code='" + this.code + "',message='" + this.getMessage() + "'}";
    }
}
