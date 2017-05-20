package com.mybatis.common.domain;

import java.io.Serializable;

/**
 * @ description:
 * @ author: guojiang.xiong
 * @ created: 2017-05-19 下午5:29
 */
public class AppContext implements Serializable {
    private static final long serialVersionUID = -6841769478216085204L;
    private String sysSource;
    private String version;
    private String token;

    public AppContext() {
    }

    public String getSysSource() {
        return this.sysSource;
    }

    public void setSysSource(String sysSource) {
        this.sysSource = sysSource;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
