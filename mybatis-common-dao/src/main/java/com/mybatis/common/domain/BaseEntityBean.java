package com.mybatis.common.domain;

import java.util.Date;

/**
 * @ description:
 * @ author: guojiang.xiong
 * @ created: 2017-05-19 下午5:30
 */
public class BaseEntityBean extends Criteria {
    private static final long serialVersionUID = -7793739903799136331L;
    private Integer sysVersion;
    private Date createTime;
    private Date updateTime;
    private String createUser;
    private String updateUser;
    private Integer yn;

    public BaseEntityBean() {
    }

    public Integer getSysVersion() {
        return this.sysVersion;
    }

    public void setSysVersion(Integer sysVersion) {
        this.sysVersion = sysVersion;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getYn() {
        return this.yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }

    public BaseEntityBean init() {
        this.createUser = "";
        this.updateUser = "";
        this.yn = 1;
        return this;
    }
}
