package com.kratos.kits.notification.config.annotation.configurer;

import com.kratos.kits.notification.NotificationProvider;
import com.kratos.kits.notification.config.annotation.NotificationBuilder;
import com.kratos.kits.notification.config.annotation.NotificationConfigurerAdapter;
import com.kratos.kits.notification.message.NotificationMessage;

public abstract class NotificationTypeConfigurer<B extends NotificationBuilder, H extends NotificationMessage> extends NotificationConfigurerAdapter<B> {
    private NotificationProvider<H> notificationProvider;
    @SuppressWarnings("unchecked")
    void setProvider(NotificationProvider<H> notificationProvider) {
        this.notificationProvider = notificationProvider;
    }

    public NotificationProvider<H> getProvider() {
        return notificationProvider;
    }
}
