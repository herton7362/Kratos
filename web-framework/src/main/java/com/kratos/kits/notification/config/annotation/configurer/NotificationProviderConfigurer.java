package com.kratos.kits.notification.config.annotation.configurer;

import com.kratos.kits.notification.NotificationProvider;
import com.kratos.kits.notification.config.annotation.NotificationConfigurerAdapter;
import com.kratos.kits.notification.message.NotificationMessageType;

public abstract class NotificationProviderConfigurer extends NotificationConfigurerAdapter {
    private NotificationProvider notificationProvider;
    @SuppressWarnings("unchecked")
    public <C extends NotificationProviderConfigurer> C setProvider(NotificationProvider notificationProvider) {
        this.notificationProvider = notificationProvider;
        return (C) this;
    }

    public NotificationProvider getProvider() {
        return notificationProvider;
    }
}
