package com.kratos.kits.notification.config.annotation.configurer;


import com.kratos.kits.notification.config.annotation.NotificationBuilder;
import com.kratos.kits.notification.config.annotation.builder.NotificationProviders;
import com.kratos.kits.notification.message.SmsVerifyCodeMessage;
import com.kratos.kits.notification.provider.SmsVerifyCodeAlidayuProvider;

public final class SmsVerifyCodeConfigurer<B extends NotificationBuilder, H extends SmsVerifyCodeMessage> extends NotificationTypeConfigurer<B, H> {
    private NotificationProviders providerConfigurer;
    public SmsVerifyCodeConfigurer(NotificationProviders providerConfigurer) {
        this.providerConfigurer = providerConfigurer;
    }
    public SmsVerifyCodeConfigurer<B, H> alidayuProvider() throws Exception {
        this.setProvider(new SmsVerifyCodeAlidayuProvider<>(providerConfigurer.getConfigurer(AlidayuProviderConfigurer.class)));
        return this;
    }
}
