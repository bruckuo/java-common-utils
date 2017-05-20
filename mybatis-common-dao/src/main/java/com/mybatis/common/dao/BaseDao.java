package com.mybatis.common.dao;

import com.mybatis.common.domain.Criteria;
import com.mybatis.common.domain.PageBean;
import com.mybatis.common.domain.Page;

import java.util.List;

/**
 * @ description:
 * @ author: guojiang.xiong
 * @ created: 2017-05-19 下午5:49
 */
@Deprecated
public interface BaseDao<M extends Criteria, PK> {
    String SQL_SELECT = "select";
    String SQL_UPDATE = "update";
    String SQL_DELETE = "delete";
    String SQL_INSERT = "insert";
    String SQL_COUNT = "count";
    String SQL_BATCH = "Batch";
    String SQL_ONE = "One";
    int DEFAULT_PAGE_SIZE = 10;
    String IBATIS_PROPERYTY_PREFIX = "_";

    int save(M modelEntity);

    int saveAll(List<M> entityList);

    int update(M modelEntry, M modelQuery);

    int update(M modelEntry);

    int updateAll(List<M> entityList);

    int saveOrUpdate(M modelEntry);

    int saveOrUpdate(List<M> entryList);

    int delete(PK id);

    int delete(M modelQuery);

    M get(PK id);

    int count(M modelQuery);

    List<M> findList(M modelQuery);

    List<M> findList(M modelQuery, int pn, int pageSize);

    PageBean<M> pageQuery(M modelQuery, PageBean<M> pageBean);

    Page<M> pageQuery(M modelQuery, Integer offset, Integer limit);

    M findOne(M modelQuery);

    boolean exists(PK id);
}

