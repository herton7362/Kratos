package com.kratos.kits.notification.config.annotation.configurer;

import com.kratos.kits.notification.config.annotation.NotificationBuilder;
import com.kratos.kits.notification.config.annotation.builder.NotificationProviders;
import com.kratos.kits.notification.message.VoiceVerifyCodeMessage;
import com.kratos.kits.notification.provider.VoiceVerifyCodeAlidayuProvider;

public class VoiceVerifyCodeConfigurer<B extends NotificationBuilder, H extends VoiceVerifyCodeMessage> extends NotificationTypeConfigurer<B, H> {
    private NotificationProviders providerConfigurer;
    public VoiceVerifyCodeConfigurer(NotificationProviders providerConfigurer) {
        this.providerConfigurer = providerConfigurer;
    }
    public VoiceVerifyCodeConfigurer<B, H> alidayuProvider() throws Exception {
        this.setProvider(new VoiceVerifyCodeAlidayuProvider<>(providerConfigurer.getConfigurer(AlidayuProviderConfigurer.class)));
        return this;
    }
}
