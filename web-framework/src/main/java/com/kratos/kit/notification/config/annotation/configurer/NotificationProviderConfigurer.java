package com.kratos.kit.notification.config.annotation.configurer;

import com.kratos.kit.notification.NotificationProvider;
import com.kratos.kit.notification.config.annotation.NotificationConfigurerAdapter;

public abstract class NotificationProviderConfigurer extends NotificationConfigurerAdapter {
    @SuppressWarnings("unchecked")
    public <C extends NotificationProviderConfigurer> C setProvider(NotificationProvider notificationProvider) {
        return (C) this;
    }
}
