package com.kratos.module.auth.domain;

import com.kratos.common.PageRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends PageRepository<Admin> {
    @Query("select m from Admin m where m.loginName=?1")
    Admin findOneByLoginName(String account);
}
