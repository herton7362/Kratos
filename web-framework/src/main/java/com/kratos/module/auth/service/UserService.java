package com.kratos.module.auth.service;

import com.kratos.entity.BaseUser;

public interface UserService {
    /**
     * 根据账户查询用户
     * @param account 账户
     * @return {@link BaseUser} 继承该实体的用户类
     */
    BaseUser findOneByLoginName(String account) throws Exception;
}
