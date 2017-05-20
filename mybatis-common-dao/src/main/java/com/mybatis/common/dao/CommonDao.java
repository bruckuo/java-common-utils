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

    int save(M modelEntry);

    int saveAll(List<M> entityList);

    int update(M modelEntry, M modelQuery);

    int update(M modelEntry);

    int updateAll(List<M> entityList);

    int saveOrUpdate(M m);

    int saveOrUpdate(List<M> entryList);

    int delete(PK id);

    int delete(M modelQuery);

    M get(PK id);

    /**
     * @deprecated
     */
    @Deprecated
    int count(M modelQuery);

    /**
     * @deprecated
     */
    @Deprecated
    List<M> findList(M modelQuery);

    /**
     * @deprecated
     */
    @Deprecated
    List<M> findList(M modelQuery, int pn, int pageSize);

    /**
     * @deprecated
     */
    @Deprecated
    PageBean<M> pageQuery(M modelQuery, PageBean<M> pageBean);

    /**
     * @deprecated
     */
    @Deprecated
    Page<M> pageQuery(M modelQuery, Integer offset, Integer limit);

    /**
     * @deprecated
     */
    @Deprecated
    M findOne(M modelQuery);

    boolean exists(PK id);

    int totalCount(C modelQuery);

    M selectOne(C modelQuery);

    List<M> selectList(C modelQuery);

    /**
     * 查询
     * @param modelQuery 条件
     * @param pageNo 页数
     * @param pageSize 条数
     * @return list
     */
    List<M> selectList(C modelQuery, int pageNo, int pageSize);

    /**
     * 分页查询
     * @param modelQuery 查询条件
     * @param offset 页数
     * @param limit 查询数量
     * @return page
     */
    Page<M> pageSelect(C modelQuery, Integer offset, Integer limit);
}

