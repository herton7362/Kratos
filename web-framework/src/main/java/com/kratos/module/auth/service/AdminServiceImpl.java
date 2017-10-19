package com.kratos.module.auth.service;

import com.kratos.common.AbstractCrudService;
import com.kratos.entity.BaseUser;
import com.kratos.module.auth.domain.Admin;
import com.kratos.module.auth.domain.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AdminServiceImpl extends AbstractCrudService<Admin> implements AdminService, UserService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin save(Admin admin) throws Exception {
        if(admin.getId() == null) {
            if(admin.getPassword() == null) {
                admin.setPassword("123456");
            }
            admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
        } else {
            if(admin.getPassword() == null) {
                Admin temp = adminRepository.findOne(admin.getId());
                admin.setPassword(temp.getPassword());
            } else {
                admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
            }
        }
        return super.save(admin);
    }

    @Override
    public BaseUser findOneByLoginName(String account) throws Exception {
        return adminRepository.findOneByLoginName(account);
    }

    @Override
    protected AdminRepository getRepository() {
        return adminRepository;
    }

}
