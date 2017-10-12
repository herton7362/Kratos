package com.kratos.demo.module.auth;


import com.kratos.demo.module.member.domain.Member;
import com.kratos.module.auth.UserThread;

public class MemberThread extends UserThread<Member> {
    private static MemberThread instance;

    private MemberThread() {
    }

    public static MemberThread getInstance() {
        if(instance == null) {
            instance = new MemberThread();
        }
        return instance;
    }
    public Member get() {
        return super.get();
    }
}
