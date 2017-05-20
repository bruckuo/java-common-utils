package com.mybatis.common.example;

import com.alibaba.fastjson.JSON;
import com.mybatis.common.example.dao.PersonDao;
import com.mybatis.common.example.domain.criteria.PersonCriteria;
import com.mybatis.common.example.domain.entity.PersonEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @ description:
 * @ author: guojiang.xiong
 * @ created: 2017-05-20 下午12:31
 */
@ContextConfiguration("classpath:spring-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonDaoTest {

    private final Logger LOGGER = LoggerFactory.getLogger(PersonDaoTest.class);

    @Resource
    private PersonDao personDao;

    @Test
    public void selectTest() {
        PersonCriteria query = new PersonCriteria();
        query.setId(1L);
        PersonEntity personEntity = this.personDao.selectOne(query);
        LOGGER.info(JSON.toJSONString(personEntity));

    }
}
