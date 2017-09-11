package com.kratos.kits.notification.config.annotation.configurer;

import com.kratos.kits.notification.config.annotation.NotificationBuilder;
import com.kratos.kits.notification.config.annotation.builder.NotificationProviders;
import com.kratos.kits.notification.message.SmsBroadcastMessage;
import com.kratos.kits.notification.provider.SmsBroadcastAlidayuProvider;

public class SmsBroadcastConfigurer<B extends NotificationBuilder, H extends SmsBroadcastMessage> extends NotificationTypeConfigurer<B, H> {
    private NotificationProviders providerConfigurer;
    public SmsBroadcastConfigurer(NotificationProviders providerConfigurer) {
        this.providerConfigurer = providerConfigurer;
    }
    public SmsBroadcastConfigurer<B, H> alidayuProvider() throws Exception {
        this.setProvider(new SmsBroadcastAlidayuProvider<>(providerConfigurer.getConfigurer(AlidayuProviderConfigurer.class)));
        return this;
    }
}
