package com.mybatis.common.example.dao.impl;

import com.mybatis.common.dao.AbstractCommonDao;
import com.mybatis.common.example.dao.PersonDao;
import com.mybatis.common.dao.TableDes;
import com.mybatis.common.example.domain.criteria.PersonCriteria;
import com.mybatis.common.example.domain.entity.PersonEntity;
import org.springframework.stereotype.Repository;

/**
 * @ description:
 * @ author: guojiang.xiong
 * @ created: 2017-05-20 下午12:19
 */
@TableDes(nameSpace = "personMapper", tableName = "Person")
@Repository
public class PersonDaoImpl extends AbstractCommonDao<PersonEntity, PersonCriteria, Long> implements PersonDao {

}