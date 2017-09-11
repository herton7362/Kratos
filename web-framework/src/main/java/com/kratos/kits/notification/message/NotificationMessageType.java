package com.kratos.kits.notification.message;

import com.kratos.kits.notification.config.annotation.NotificationConfigurer;
import com.kratos.kits.notification.config.annotation.configurer.*;

public enum NotificationMessageType {
    SMS_VERIFY_CODE(SmsVerifyCodeConfigurer.class),
    VOICE_VERIFY_CODE(VoiceVerifyCodeConfigurer.class),
    SMS_BROADCAST(SmsBroadcastConfigurer.class),
    MOBILE_APP_BROADCAST(MobileAppBroadcastConfigurer.class);

    private Class<? extends NotificationConfigurer> configurerClass;

    <C extends NotificationTypeConfigurer> NotificationMessageType(Class<C> clazz) {
        this.configurerClass = clazz;
    }

    @SuppressWarnings("unchecked")
    public <C extends NotificationConfigurer> Class<C> getConfigurerClass() {
        return (Class<C>) configurerClass;
    }
}
