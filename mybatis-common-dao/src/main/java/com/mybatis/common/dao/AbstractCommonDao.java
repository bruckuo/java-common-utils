package com.mybatis.common.dao;

import com.mybatis.common.utils.BeanHelper;
import com.mybatis.common.domain.Criteria;
import com.mybatis.common.domain.Page;
import com.mybatis.common.domain.PageBean;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @ description:
 * @ author: guojiang.xiong
 * @ created: 2017-05-19 下午5:42
 */
public abstract class AbstractCommonDao<M extends Criteria, C extends M, PK> extends SqlSessionDaoSupport implements CommonDao<M, C, PK> {
    private final String sql_select;
    private final String sql_select_one;
    private final String sql_count;
    private final String sql_insert;
    private final String sql_insert_batch;
    private final String sql_update;
    private final String sql_update_batch;
    private final String sql_delete;
    private final String sql_delete_one;
    private final String nameSpace;

    public AbstractCommonDao() {
        TableDes myTable = this.getClass().getAnnotation(TableDes.class);
        Assert.notNull(myTable);
        String tableName = myTable.tableName();
        Assert.notNull(tableName);
        String nameSpaceTmp = myTable.nameSpace();
        this.nameSpace = StringUtils.isEmpty(nameSpaceTmp) ? "" : nameSpaceTmp + ".";
        this.sql_select = "select" + tableName;
        this.sql_select_one = "select" + "One" + tableName;
        this.sql_insert = "insert" + tableName;
        this.sql_insert_batch = "insert" + tableName + "Batch";
        this.sql_update = "update" + tableName;
        this.sql_update_batch = "update" + tableName + "Batch";
        this.sql_delete = "delete" + tableName;
        this.sql_delete_one = "delete" + "One" + tableName;
        this.sql_count = "count" + tableName;
    }

    public int save(M modelEntity) {
        return this.save(this.sql_insert, modelEntity);
    }

    public int saveAll(List<M> entityList) {
        return this.saveAll(this.sql_insert_batch, entityList);
    }

    public int update(M modelEntry, M modelQuery) {
        return this.update(this.sql_update, modelEntry, modelQuery);
    }

    public int updateAll(List<M> entityList) {
        return this.updateAll(this.sql_update_batch, entityList);
    }

    public int update(M modelEntry) {
        return this.update(this.sql_update, modelEntry);
    }

    public int saveOrUpdate(M modelEntry) {
        return this.saveOrUpdate(this.sql_insert, this.sql_update, modelEntry);
    }

    public int saveOrUpdate(List<M> entryList) {
        return this.saveOrUpdate(this.sql_insert, this.sql_update, entryList);
    }

    public int delete(PK id) {
        return this.delete(this.sql_delete_one, id);
    }

    public int delete(M modelQuery) {
        return this.delete(this.sql_delete, modelQuery);
    }

    public M get(PK id) {
        return this.get(this.sql_select_one, id);
    }

    public int count(M modelQuery) {
        return this.count(this.sql_count, modelQuery);
    }

    public List<M> findList(M modelQuery) {
        return this.findList(this.sql_select, modelQuery);
    }

    public List<M> findList(M modelQuery, int pn, int pageSize) {
        return this.findList(this.sql_select, modelQuery, pn, pageSize);
    }

    public PageBean<M> pageQuery(M modelQuery, PageBean<M> pageBean) {
        Assert.notNull(modelQuery, "查询条件不能为空.");
        Assert.notNull(pageBean, "分页条件不能为空.");
        int count = this.count(this.sql_count, modelQuery);
        pageBean.setTotalCount((long) count);
        if (count == 0) {
            return pageBean;
        }
        pageBean.setResultList(this.findList(this.sql_select, modelQuery, (int) pageBean.getPageNo(), pageBean.getPageSize()));
        return pageBean;
    }

    public Page<M> pageQuery(M modelQuery, Integer offset, Integer limit) {
        Page<M> page = new Page<M>();
        Assert.notNull(modelQuery, "查询条件不能为空.");
        int count = this.count(this.sql_count, modelQuery);
        page.setTotal(count);
        if (count == 0) {
            return page;
        }
        page.setRows(this.findList4Offset(this.sql_select, modelQuery, offset, limit));
        return page;
    }

    public M findOne(M modelQuery) {
        return this.findOne(this.sql_select, modelQuery);
    }

    public boolean exists(PK id) {
        return this.exists(this.sql_select, id);
    }

    protected int save(String statement, M modelEntity) {
        return this.getSqlSession().insert(this.addNameSpace(statement), modelEntity);
    }

    protected int saveOrUpdate(String insertStatement, String updateStatement, M modelEntry) {
        return this.getPrimaryFieldValue(modelEntry) == null ? this.save(insertStatement, modelEntry) : this.update(updateStatement, modelEntry);
    }

    protected int saveOrUpdate(String insertStatement, String updateStatement, List<M> entityList) {
        int effectCount = 0;
        if (CollectionUtils.isEmpty(entityList)) {
            return effectCount;
        }
        List<M> addList = new ArrayList<M>();
        List<M> updateList = new ArrayList<M>();

        for (M modelEntry : entityList) {
            if (this.getPrimaryFieldValue(modelEntry) == null) {
                addList.add(modelEntry);
                continue;
            }
            updateList.add(modelEntry);
        }

        if (CollectionUtils.isNotEmpty(addList)) {
            effectCount += this.saveAll(insertStatement, addList);
        }

        if (CollectionUtils.isNotEmpty(updateList)) {
            effectCount += this.updateAll(updateStatement, updateList);
        }

        return effectCount;
    }

    protected int count(String statement, Criteria criteria) {
        Integer result = (Integer) this.getSqlSession().selectOne(this.addNameSpace(statement), criteria);
        return result == null ? 0 : result;
    }

    protected int update(String statement, M modelEntry, M modelQuery) {
        Map<String, Object> map = BeanHelper.modelToMap(modelEntry, "", "");
        map.putAll(BeanHelper.modelToMap(modelQuery, "_", ""));
        return this.getSqlSession().update(this.addNameSpace(statement), map);
    }

    protected int updateAll(String statement, List<M> entityList) {
        return this.getSqlSession().update(this.addNameSpace(statement), entityList);
    }

    protected int update(String statement, M modelEntry) {
        Map<String, Object> map = BeanHelper.modelToMap(modelEntry, "", "");
        return this.getSqlSession().update(this.addNameSpace(statement), map);
    }

    protected M get(String statement, PK id) {
        return this.getSqlSession().selectOne(this.addNameSpace(statement), id);
    }

    protected List<M> findList(String statement, Criteria criteria) {
        return this.getSqlSession().selectList(this.addNameSpace(statement), criteria);
    }

    protected <T> List<T> findList(String statement, Map<String, Object> modelQuery) {
        return this.getSqlSession().selectList(this.addNameSpace(statement), modelQuery);
    }

    protected List<M> findList(String statement, Criteria criteria, int pn, int pageSize) {
        return this.findList(statement, this.buildCriteria(criteria, pn, pageSize));
    }

    protected List<M> findList4Offset(String statement, Criteria criteria, Integer offset, Integer limit) {
        return this.findList(statement, this.buildCriteria4Offset(criteria, offset, limit));
    }

    protected <T> List<T> findList(String statement, Object entity) {
        return this.getSqlSession().selectList(this.addNameSpace(statement), entity);
    }

    protected boolean exists(String statement, PK id) {
        return this.get(statement, id) == null ? Boolean.FALSE : Boolean.TRUE;
    }

    protected int saveAll(String statement, List<M> list) {
        return this.getSqlSession().insert(this.addNameSpace(statement), list);
    }

    protected int delete(String statement, Object modelQuery) {
        return this.getSqlSession().delete(this.addNameSpace(statement), modelQuery);
    }

    protected <T> T findOne(String statement, T modelQuery) {
        return this.getSqlSession().selectOne(this.addNameSpace(statement), modelQuery);
    }

    protected <T> PageBean<T> pageQuery(String queryCountSql, String queryListSql, Criteria modelQuery, PageBean<T> pageBean) {
        Assert.notNull(pageBean, "分页条件不能为空.");
        pageBean.setTotalCount((long) this.count(queryCountSql, modelQuery));
        pageBean.setResultList((Collection<T>) this.findList(queryListSql, this.buildCriteria(modelQuery, (int) pageBean.getPageNo(), pageBean.getPageSize())));
        return pageBean;
    }

    private String addNameSpace(String statement) {
        return this.nameSpace + statement;
    }

    private Criteria buildCriteria(Criteria criteria, int pn, int pageSize) {
        Assert.notNull(criteria, "查询条件不能为空.");
        int skipResults = pn > 1 ? (pn - 1) * pageSize : 0;
        int maxResults = pageSize > 0 ? pageSize : 10;
        criteria.addExtField("start", skipResults);
        criteria.addExtField("limit", maxResults);
        return criteria;
    }

    private Criteria buildCriteria4Offset(Criteria criteria, Integer offset, Integer limit) {
        Assert.notNull(criteria, "查询条件不能为空.");
        criteria.addExtField("start", offset);
        criteria.addExtField("limit", limit);
        return criteria;
    }

    private Object getPrimaryFieldValue(M modelEntry) {
        Class clazz = modelEntry.getClass();
        Field field = this.findPrimaryField(clazz);
        if (field == null) {
            throw new RuntimeException("方法不支持该实体对象的[保存或更新]操作");
        }
        field.setAccessible(true);
        try {
            return field.get(modelEntry);
        } catch (IllegalAccessException var6) {
            throw new RuntimeException(var6);
        }
    }

    private Field findPrimaryField(Class clazz) {
        while (clazz != Object.class) {
            Field[] fs = clazz.getDeclaredFields();
            for (Field field : fs) {
                if ("ID".equals(field.getName().toUpperCase())) {
                    return field;
                }
            }
            clazz = clazz.getSuperclass();
        }
        return null;
    }

    public int totalCount(C modelQuery) {
        return this.count(this.sql_count, modelQuery);
    }

    public M selectOne(C modelQuery) {
        return this.findOne(this.sql_select_one, modelQuery);
    }

    public List<M> selectList(C modelQuery) {
        return this.findList(this.sql_select, modelQuery);
    }

    public List<M> selectList(C modelQuery, int pn, int pageSize) {
        return this.findList(this.sql_select, modelQuery, pn, pageSize);
    }

    public Page<M> pageSelect(C modelQuery, Integer offset, Integer limit) {
        Page<M> page = new Page<M>();
        Assert.notNull(modelQuery, "查询条件不能为空.");
        int count = this.count(this.sql_count, modelQuery);
        page.setTotal(count);
        if (count == 0)
            return page;
        page.setRows(this.findList4Offset(this.sql_select, modelQuery, offset, limit));
        return page;
    }
}

