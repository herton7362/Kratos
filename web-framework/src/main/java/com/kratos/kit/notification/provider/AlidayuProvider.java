package com.kratos.kit.notification.provider;

import com.kratos.kit.notification.NotificationProvider;
import com.kratos.kit.notification.message.NotificationMessage;

public class AlidayuProvider implements NotificationProvider {
    private String smsType;
    private String smsFreeSignName;
    private String smsTemplateCode;
    private String serverUrl;
    private String appId;
    private String appSecret;

    @Override
    public boolean send(NotificationMessage message) throws Exception {
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
