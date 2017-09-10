package com.kratos.kit.notification.config.annotation.configurer;

import com.kratos.kit.notification.config.annotation.filter.SendFilter;

public final class SendConfigurer {
    private SendFilter sendFilter;
    public void setSendFilter(SendFilter sendFilter) throws Exception {
        this.sendFilter = sendFilter;
    }

    public SendFilter getSendFilter() {
        return sendFilter;
    }
}
