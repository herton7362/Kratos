package com.kratos.demo.module.member.service;

import com.kratos.common.AbstractCrudService;
import com.kratos.common.PageRepository;
import com.kratos.demo.module.member.domain.Member;
import com.kratos.demo.module.member.domain.MemberRepository;
import com.kratos.module.auth.domain.Admin;
import com.kratos.module.auth.service.OauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class MemberServiceImpl extends AbstractCrudService<Member> implements MemberService {
    private final MemberRepository repository;

    @Override
    protected PageRepository<Member> getRepository() {
        return repository;
    }

    @Override
    public Member findOneByLoginName(String loginName) {
        return repository.findOneByLoginName(loginName);
    }

    @Override
    public Member save(Member member) {
        if(member.getId() != null) {
            if(member.getPassword() == null) {
                Member temp = repository.findOne(member.getId());
                member.setPassword(temp.getPassword());
            } else {
                member.setPassword(new BCryptPasswordEncoder().encode(member.getPassword()));
            }
        } else {
            member.setPassword(new BCryptPasswordEncoder().encode(member.getPassword()));
        }
        return super.save(member);
    }

    @Autowired
    public MemberServiceImpl(MemberRepository repository) {
        this.repository = repository;
    }
}
