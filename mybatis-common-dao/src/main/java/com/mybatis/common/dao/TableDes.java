package com.mybatis.common.dao;

import java.lang.annotation.*;

/**
 * @ description:
 * @ author: guojiang.xiong
 * @ created: 2017-05-19 下午5:48
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface TableDes {
    String nameSpace() default "";

    String tableName() default "";
}
