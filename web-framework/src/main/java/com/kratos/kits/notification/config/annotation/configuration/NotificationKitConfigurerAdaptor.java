package com.kratos.kits.notification.config.annotation.configuration;

import com.kratos.entity.BaseUser;
import com.kratos.kits.notification.config.annotation.builder.NotificationProviders;
import com.kratos.kits.notification.config.annotation.configurer.SendConfigurer;
import com.kratos.kits.notification.message.NotificationMessageType;
import com.kratos.kits.notification.provider.AlidayuProvider;

public class NotificationKitConfigurerAdaptor implements NotificationKitConfigurer {

    @Override
    public void configure(NotificationProviders notificationProvider) throws Exception {
        AlidayuProvider alidayuProvider = new AlidayuProvider();
        notificationProvider.smsVerifyCode().setProvider(alidayuProvider);
    }

    @Override
    public void configure(SendConfigurer sendConfigurer) throws Exception {
        sendConfigurer.setSendFilter((BaseUser destUser, NotificationMessageType messageType) -> true);
    }

}
