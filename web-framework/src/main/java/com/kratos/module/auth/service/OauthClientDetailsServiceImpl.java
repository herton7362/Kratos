package com.kratos.module.auth.service;

import com.kratos.common.AbstractCrudService;
import com.kratos.common.PageRepository;
import com.kratos.module.auth.domain.OauthClientDetails;
import com.kratos.module.auth.domain.OauthClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class OauthClientDetailsServiceImpl extends AbstractCrudService<OauthClientDetails> implements OauthClientDetailsService {
    private final OauthClientDetailsRepository oauthClientDetailsRepository;


    @Override
    protected PageRepository<OauthClientDetails> getRepository() {
        return oauthClientDetailsRepository;
    }

    @Override
    public OauthClientDetails findOneByClientId(String clientId) throws Exception {
        return oauthClientDetailsRepository.findOneByClientId(clientId);
    }

    @Autowired
    public OauthClientDetailsServiceImpl(OauthClientDetailsRepository oauthClientDetailsRepository) {
        this.oauthClientDetailsRepository = oauthClientDetailsRepository;
    }
}
