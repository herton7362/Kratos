package com.kratos.module.auth;

import com.kratos.entity.BaseUser;
import com.kratos.module.auth.domain.AdminRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

public abstract class JdbcUserDetailService implements UserDetailsService {
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BaseUser user = adminRepository.findOneByLoginName(username);
        if(user == null) {
            return null;
        }
        return new User(username, user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(user.getUserType())));
    }

    public JdbcUserDetailService(
            AdminRepository adminRepository
    ) {
        this.adminRepository = adminRepository;
    }
}
