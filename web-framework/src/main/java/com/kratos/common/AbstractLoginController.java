package com.kratos.common;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 提供登录注册等服务
 */
public abstract class AbstractLoginController {
    private final LoginService loginService;
    /**
     * 发送短信验证码
     */
    @ApiOperation(value="发送短信验证码")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "mobile", value = "手机号码", dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/api/verifyCode", method = RequestMethod.GET)
    public ResponseEntity sendVerifyCode(@RequestParam(value = "mobile") String mobile) throws Exception {
        loginService.sendVerifyCode(mobile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 修改密码
     */
    @ApiOperation(value="修改密码")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "mobile", value = "手机号码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "code", value = "验证码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "repassword", value = "确认密码", dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/api/editPwd", method = RequestMethod.GET)
    public ResponseEntity editPwd(
            @RequestParam(value = "mobile") String mobile,
            @RequestParam(value = "code") String code,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "repassword") String repassword
    ) throws Exception {
        loginService.editPwd(mobile, code, password, repassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 登录
     */
    @ApiOperation(value="登录")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "appId", value = "app_id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "appSecret", value = "app_secret", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "mobile", value = "手机号码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/api/login", method = RequestMethod.GET)
    public ResponseEntity<OAuth2AccessToken> login(
            @RequestParam(value = "appId") String appId,
            @RequestParam(value = "appSecret") String appSecret,
            @RequestParam(value = "mobile") String mobile,
            @RequestParam(value = "password") String password
    ) throws Exception {
        OAuth2AccessToken oAuth2AccessToken = loginService.login(appId, appSecret, mobile, password);
        return new ResponseEntity<>(oAuth2AccessToken, HttpStatus.OK);
    }

    /**
     * 注册
     */
    @ApiOperation(value="注册")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "mobile", value = "手机号码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "code", value = "验证码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "repassword", value = "确认密码", dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/api/register", method = RequestMethod.POST)
    public ResponseEntity register(
            @RequestParam(value = "mobile") String mobile,
            @RequestParam(value = "code") String code,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "repassword") String repassword
    ) throws Exception {
        loginService.register(mobile, code, password, repassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public AbstractLoginController(LoginService loginService) {
        this.loginService = loginService;
    }
}
