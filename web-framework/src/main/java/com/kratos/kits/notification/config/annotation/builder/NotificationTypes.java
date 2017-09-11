package com.kratos.kits.notification.config.annotation.builder;

import com.kratos.kits.notification.config.annotation.AbstractNotificationBuilder;
import com.kratos.kits.notification.config.annotation.configurer.MobileAppBroadcastConfigurer;
import com.kratos.kits.notification.config.annotation.configurer.SmsBroadcastConfigurer;
import com.kratos.kits.notification.config.annotation.configurer.SmsVerifyCodeConfigurer;
import com.kratos.kits.notification.config.annotation.configurer.VoiceVerifyCodeConfigurer;
import com.kratos.kits.notification.message.MobileAppBroadcastMessage;
import com.kratos.kits.notification.message.SmsBroadcastMessage;
import com.kratos.kits.notification.message.SmsVerifyCodeMessage;
import com.kratos.kits.notification.message.VoiceVerifyCodeMessage;

public class NotificationTypes extends AbstractNotificationBuilder<NotificationTypes> {
    private NotificationProviders providerConfigurer;
    public NotificationTypes(NotificationProviders providerConfigurer) {
        this.providerConfigurer = providerConfigurer;
    }
    public SmsVerifyCodeConfigurer<NotificationTypes, SmsVerifyCodeMessage> smsVerifyCode() throws Exception {
        return getOrApply(new SmsVerifyCodeConfigurer<NotificationTypes, SmsVerifyCodeMessage>(providerConfigurer));
    }

    public VoiceVerifyCodeConfigurer<NotificationTypes, VoiceVerifyCodeMessage> voiceVerifyCode() throws Exception {
        return getOrApply(new VoiceVerifyCodeConfigurer<NotificationTypes, VoiceVerifyCodeMessage>(providerConfigurer));
    }

    public SmsBroadcastConfigurer<NotificationTypes, SmsBroadcastMessage> smsBroadcast() throws Exception {
        return getOrApply(new SmsBroadcastConfigurer<NotificationTypes, SmsBroadcastMessage>(providerConfigurer));
    }

    public MobileAppBroadcastConfigurer<NotificationTypes, MobileAppBroadcastMessage> mobileAppBroadcast() throws Exception {
        return getOrApply(new MobileAppBroadcastConfigurer<NotificationTypes, MobileAppBroadcastMessage>(providerConfigurer));
    }
}
