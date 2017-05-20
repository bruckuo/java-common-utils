package com.mybatis.common.dao;

import com.mybatis.common.domain.Criteria;
import com.mybatis.common.domain.Page;
import com.mybatis.common.domain.PageBean;

import java.util.List;

/**
 * @ description:
 * @ author: guojiang.xiong
 * @ created: 2017-05-19 下午5:46
 */
public interface CommonDao<M extends Criteria, C extends M, PK> {
    String SQL_SELECT = "select";
    String SQL_UPDATE = "update";
    String SQL_DELETE = "delete";
    String SQL_INSERT = "insert";
    String SQL_COUNT = "count";
    String SQL_BATCH = "Batch";
    String SQL_ONE = "One";
    int DEFAULT_PAGE_SIZE = 10;
    String IBATIS_PROPERYTY_PREFIX = "_";

    int save(M var1);

    int saveAll(List<M> var1);

    int update(M var1, M var2);

    int update(M var1);

    int updateAll(List<M> var1);

    int saveOrUpdate(M var1);

    int saveOrUpdate(List<M> var1);

    int delete(PK var1);

    int delete(M var1);

    M get(PK var1);

    /**
     * @deprecated
     */
    @Deprecated
    int count(M var1);

    /**
     * @deprecated
     */
    @Deprecated
    List<M> findList(M var1);

    /**
     * @deprecated
     */
    @Deprecated
    List<M> findList(M var1, int var2, int var3);

    /**
     * @deprecated
     */
    @Deprecated
    PageBean<M> pageQuery(M var1, PageBean<M> var2);

    /**
     * @deprecated
     */
    @Deprecated
    Page<M> pageQuery(M var1, Integer var2, Integer var3);

    /**
     * @deprecated
     */
    @Deprecated
    M findOne(M var1);

    boolean exists(PK var1);

    int totalCount(C var1);

    M selectOne(C var1);

    List<M> selectList(C var1);

    List<M> selectList(C var1, int var2, int var3);

    Page<M> pageSelect(C var1, Integer var2, Integer var3);
}

