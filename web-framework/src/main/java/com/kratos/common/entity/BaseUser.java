package com.kratos.common.entity;

import javax.persistence.MappedSuperclass;

/**
 * 基础用户对象
 * @author tang he
 * @since 1.0.0
 */
@MappedSuperclass
public abstract class BaseUser extends BaseEntity {
    private String loginName;
    private String password;
    private String mobile;
    private String userType;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserType() {
        return userType;
    }

    protected void setUserType(String userType) {
        this.userType = userType;
    }

    public enum UserType {
        MEMBER,ADMIN
    }
}
