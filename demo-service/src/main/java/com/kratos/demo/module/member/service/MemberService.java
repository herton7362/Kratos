package com.kratos.demo.module.member.service;

import com.kratos.demo.module.member.domain.Member;
import com.kratos.common.CrudService;

public interface MemberService extends CrudService<Member> {
    /**
     * 根据登录名获取会员
     * @param loginName 登录名
     * @return {@link Member}
     */
    Member findOneByLoginName(String loginName);
}
