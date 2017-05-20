package com.mybatis.common.example.dao;

import com.mybatis.common.dao.CommonDao;
import com.mybatis.common.example.domain.criteria.PersonCriteria;
import com.mybatis.common.example.domain.entity.PersonEntity;

/**
 * @ description:
 * @ author: guojiang.xiong
 * @ created: 2017-05-20 下午12:18
 */
public interface PersonDao extends CommonDao<PersonEntity, PersonCriteria, Long> {

}
