package com.mybatis.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @ description:
 * @ author: guojiang.xiong
 * @ created: 2017-05-19 下午5:51
 */
public class MoneyUtils {
    public static String fen2yuan(Long fee) {
        return (new BigDecimal(fee.longValue())).divide(new BigDecimal("100.00"), 2, RoundingMode.HALF_UP) + "";
    }

    public static BigDecimal fen2yuanBigDecimal(Long fee) {
        return (new BigDecimal(fee.longValue())).divide(new BigDecimal("100.00"), 2, RoundingMode.HALF_UP);
    }

    public static int yuan2fen(String yuan) {
        return (new BigDecimal(yuan)).setScale(2, RoundingMode.FLOOR).multiply(new BigDecimal("100")).intValue();
    }
}
