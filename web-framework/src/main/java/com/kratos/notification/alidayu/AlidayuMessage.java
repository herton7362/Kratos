package com.kratos.notification.alidayu;

import com.kratos.notification.NotificationMessage;

import java.util.Map;

public class AlidayuMessage extends NotificationMessage {
    private String templateCode;
    private Map<String, Object> params;

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
