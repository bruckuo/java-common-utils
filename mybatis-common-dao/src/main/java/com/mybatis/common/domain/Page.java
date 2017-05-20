package com.mybatis.common.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @ description:
 * @ author: guojiang.xiong
 * @ created: 2017-05-19 下午5:35
 */
public class Page<T> implements Serializable {
    private static final long serialVersionUID = -8387562513921100674L;
    private int total;
    private List<T> rows;

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return this.rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
