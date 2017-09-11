package com.kratos.kits.notification.config.annotation.configurer;

import com.kratos.kits.notification.config.annotation.NotificationBuilder;
import com.kratos.kits.notification.config.annotation.builder.NotificationProviders;
import com.kratos.kits.notification.message.MobileAppBroadcastMessage;
import com.kratos.kits.notification.provider.MobileAppBroadcastAlidayuProvider;

public class MobileAppBroadcastConfigurer<B extends NotificationBuilder, H extends MobileAppBroadcastMessage> extends NotificationTypeConfigurer<B, H> {
    private NotificationProviders providerConfigurer;
    public MobileAppBroadcastConfigurer(NotificationProviders providerConfigurer) {
        this.providerConfigurer = providerConfigurer;
    }
    public MobileAppBroadcastConfigurer<B, H> alidayuProvider() throws Exception {
        this.setProvider(new MobileAppBroadcastAlidayuProvider<>(providerConfigurer.getConfigurer(AlidayuProviderConfigurer.class)));
        return this;
    }
}
