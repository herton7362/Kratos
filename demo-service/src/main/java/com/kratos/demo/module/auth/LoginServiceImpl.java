package com.kratos.demo.module.auth;

import com.kratos.common.AbstractLoginService;
import com.kratos.demo.config.OAuth2Properties;
import com.kratos.entity.BaseUser;
import com.kratos.kits.Kits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class LoginServiceImpl extends AbstractLoginService {
    private final OAuth2Properties oAuth2Properties;
    @Override
    public void editPwd(String mobile, String code, String password, String repassword) throws Exception {

    }

    @Override
    public String getAccessTokenUri() {
        return oAuth2Properties.getAccessTokenUri();
    }

    @Override
    public void register(String mobile, String code, String password, String repassword) throws Exception {

    }

    @Override
    public BaseUser findUserByMobile(String mobile) throws Exception {
        return null;
    }

    @Autowired
    public LoginServiceImpl(
            Kits kits,
            OAuth2ClientContext clientContext,
            OAuth2Properties oAuth2Properties
    ) {
        super(kits, clientContext);
        this.oAuth2Properties = oAuth2Properties;
    }
}
