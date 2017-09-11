package com.kratos.kits.notification.config.annotation.builder;

import com.kratos.kits.notification.config.annotation.AbstractNotificationBuilder;
import com.kratos.kits.notification.config.annotation.configurer.AlidayuProviderConfigurer;

/**
 * 消息提供商构建
 * @author tang he
 * @since 1.0.0
 */
public class NotificationProviders extends AbstractNotificationBuilder<NotificationProviders> {
    public AlidayuProviderConfigurer<NotificationProviders> alidayuProvider() throws Exception {
        return getOrApply(new AlidayuProviderConfigurer<NotificationProviders>());
    }
}
