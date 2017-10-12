package com.kratos.demo.config;

import com.kratos.demo.module.member.domain.MemberRepository;
import com.kratos.common.utils.NetworkUtils;
import com.kratos.common.utils.SpringUtils;
import com.kratos.common.utils.StringUtils;
import com.kratos.entity.BaseUser;
import com.kratos.module.auth.UserThread;
import com.kratos.module.auth.domain.AdminRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonInterceptor extends HandlerInterceptorAdapter {
    private MemberRepository memberRepository;
    private AdminRepository adminRepository;
    private TokenStore tokenStore;
    @Override
    @SuppressWarnings("unchecked")
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        memberRepository = SpringUtils.getBean(MemberRepository.class);
        adminRepository = SpringUtils.getBean(AdminRepository.class);
        tokenStore = SpringUtils.getBean(TokenStore.class);
        String accessToken = request.getParameter("access_token");
        UserThread.getInstance().setIpAddress(NetworkUtils.getIpAddress(request));
        if(StringUtils.isNotBlank(accessToken)) {
            OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(accessToken);
            User user = (User) oAuth2Authentication.getPrincipal();
            UserThread.getInstance().setClientId(oAuth2Authentication.getOAuth2Request().getClientId());
            oAuth2Authentication.getUserAuthentication().getAuthorities().forEach(grantedAuthority -> {
                BaseUser baseUser;
                if(grantedAuthority.getAuthority().equals(BaseUser.UserType.MEMBER.name())) {
                    baseUser = memberRepository.findOneByLoginName(user.getUsername());
                } else {
                    baseUser = adminRepository.findOneByLoginName(user.getUsername());
                }
                baseUser.setPassword(null);
                UserThread.getInstance().set(baseUser);
            });
        }
        return true;
    }
}
