package com.kratos.demo.module.member.web;

import com.kratos.common.AbstractCrudController;
import com.kratos.common.CrudService;
import com.kratos.demo.module.member.domain.Member;
import com.kratos.demo.module.member.service.MemberService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "会员管理")
@RestController
@RequestMapping("/api/member")
public class MemberController extends AbstractCrudController<Member> {
    private final MemberService memberService;
    @Override
    protected CrudService<Member> getService() {
        return memberService;
    }


    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}