package com.mybatis.common.example.domain.entity;

import com.mybatis.common.domain.EntityBean;

/**
 * @ description:
 * @ author: guojiang.xiong
 * @ created: 2017-05-20 下午12:13
 */
public class PersonEntity extends EntityBean {
    /**
     * id
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;
    private Integer age;
    private java.util.Date createTime;
    private String createPin;
    private java.util.Date updateTime;
    private String updatePin;
    private Integer yn;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Integer getAge(){
        return age;
    }
    public void setAge(Integer age){
        this.age = age;
    }
    public String getCreatePin(){
        return createPin;
    }
    public void setCreatePin(String createPin){
        this.createPin = createPin;
    }
    public String getUpdatePin(){
        return updatePin;
    }
    public void setUpdatePin(String updatePin){
        this.updatePin = updatePin;
    }
}
