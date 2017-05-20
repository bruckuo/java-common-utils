package com.mybatis.common.utils;

import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * @ description:
 * @ author: guojiang.xiong
 * @ created: 2017-05-19 下午5:31
 */
public class MessageUtils {
    private static MessageSource messageSource;

    public MessageUtils() {
    }

    public static String message(String code, Object... args) {
        if (messageSource == null) {
            messageSource = (MessageSource) SpringUtils.getBean(MessageSource.class);
        }

        return messageSource.getMessage(code, args, (Locale) null);
    }
}
