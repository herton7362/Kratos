package com.kratos.demo.module.member.service;

import com.kratos.common.AbstractCrudService;
import com.kratos.common.PageRepository;
import com.kratos.demo.module.member.domain.Member;
import com.kratos.demo.module.member.domain.MemberRepository;
import com.kratos.module.auth.service.OauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class MemberServiceImpl extends AbstractCrudService<Member> implements MemberService {
    private final MemberRepository repository;
    private final OauthClientDetailsService oauthClientDetailsService;

    @Override
    protected PageRepository<Member> getRepository() {
        return repository;
    }

    @Override
    public Member findOneByLoginName(String loginName) {
        return repository.findOneByLoginName(loginName);
    }


    @Autowired
    public MemberServiceImpl(
            MemberRepository repository,
            OauthClientDetailsService oauthClientDetailsService
    ) {
        this.repository = repository;
        this.oauthClientDetailsService = oauthClientDetailsService;
    }
}
