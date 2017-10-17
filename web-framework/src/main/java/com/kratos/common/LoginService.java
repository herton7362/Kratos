package com.kratos.common;

import com.kratos.common.utils.CacheUtils;
import com.kratos.entity.BaseUser;
import com.kratos.kits.Kits;
import com.kratos.kits.notification.message.SmsVerifyCodeMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * 提供登录、注册、找回密码、发送验证码等功能
 */
public abstract class LoginService {
    private final Kits kits;
    private final OAuth2ClientContext clientContext;

    /**
     * 发送短信验证码
     * @param mobile 手机号码
     */
    void sendVerifyCode(String mobile) throws Exception {
        String code = generateVerifyCode();
        CacheUtils.getInstance().add(mobile, code);
        SmsVerifyCodeMessage message = new SmsVerifyCodeMessage();
        message.setDestUser(findUserByMobile(mobile));
        message.setVerifyCode(String.format("您的手机验证码：%s，5分钟内有效，请勿泄露。如非本人操作，请忽略此短信，谢谢。", code));
        kits.notification().send(message);
    }

    /**
     * 修改密码
     * @param mobile 手机号码
     * @param code 短信验证码
     * @param password 密码
     * @param repassword 确认密码
     */
    abstract void editPwd(String mobile, String code, String password, String repassword) throws Exception;

    /**
     * 登录
     * @param appId app_id
     * @param appSecret app_secret
     * @param mobile 手机号码
     * @param password 密码
     * @return {@link OAuth2AccessToken} token
     */
    OAuth2AccessToken login(String appId, String appSecret, String mobile, String password) throws Exception {
        ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();
        details.setId("framework/tonr");
        details.setClientId(appId);
        details.setClientSecret(appSecret);
        details.setUsername(mobile);
        details.setPassword(password);
        details.setAccessTokenUri(getAccessTokenUri());
        details.setScope(Arrays.asList("read", "write", "trust"));
        OAuth2RestTemplate template = new OAuth2RestTemplate(details, clientContext);
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON,
                MediaType.valueOf("text/javascript")));
        template.setMessageConverters(Collections.singletonList(converter));
        return template.getAccessToken();
    }

    /**
     * 返回请求accessToken的链接地址
     * @return 链接地址
     */
    abstract String getAccessTokenUri();

    /**
     * 注册
     * @param mobile 手机号码
     * @param code 短信验证码
     * @param password 密码
     * @param repassword 确认密码
     */
    abstract void register(String mobile, String code, String password, String repassword) throws Exception;

    /**
     * 根据手机号获取用户
     * @param mobile 手机号
     * @return {@link BaseUser} 继承了此实体的用户
     */
    abstract BaseUser findUserByMobile(String mobile) throws Exception;

    /**
     * 生成短信验证码
     * @return 验证码
     */
    private String generateVerifyCode() throws Exception {
        Random r = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            code.append(r.nextInt(10));
        }
        return code.toString();
    }

    /**
     * 验证验证码是否正确
     * @param mobile 手机号
     * @param verifyCode 验证码
     * @return 验证码是否正确
     */
    Boolean verifyVerifyCode(String mobile, String verifyCode) throws Exception {
        return  verifyCode.equals(CacheUtils.getInstance().get(mobile));
    }

    public LoginService(
            Kits kits,
            OAuth2ClientContext clientContext
    ) {
        this.kits = kits;
        this.clientContext = clientContext;
    }
}
