package com.kratos.kits.notification.provider;

import com.kratos.kits.notification.NotificationProvider;
import com.kratos.kits.notification.message.NotificationMessage;
import com.kratos.kits.notification.message.SmsVerifyCodeMessage;

public class AlidayuProvider implements NotificationProvider {
    private String smsType;
    private String smsFreeSignName;
    private String smsTemplateCode;
    private String serverUrl;
    private String appId;
    private String appSecret;

    @Override
    public boolean send(NotificationMessage message) throws Exception {
        SmsVerifyCodeMessage message1 = (SmsVerifyCodeMessage) message;

        System.out.println("短信 配置:smsType=" + smsType);
        System.out.println("smsFreeSignName=" + smsFreeSignName);
        System.out.println("smsTemplateCode=" + smsTemplateCode);
        System.out.println("serverUrl=" + serverUrl);
        System.out.println("appId=" + appId);
        System.out.println("appSecret=" + appSecret);
        System.out.println("发送短信 dest:" + message.getDestUser() + " code:" + message1.getVerifyCode());
        return false;
    }

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public String getSmsFreeSignName() {
        return smsFreeSignName;
    }

    public void setSmsFreeSignName(String smsFreeSignName) {
        this.smsFreeSignName = smsFreeSignName;
    }

    public String getSmsTemplateCode() {
        return smsTemplateCode;
    }

    public void setSmsTemplateCode(String smsTemplateCode) {
        this.smsTemplateCode = smsTemplateCode;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
