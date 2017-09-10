package com.kratos.kit.notification.config.annotation.configuration;

import com.kratos.common.entity.BaseUser;
import com.kratos.kit.notification.config.annotation.builder.NotificationProviders;
import com.kratos.kit.notification.config.annotation.configurer.SendConfigurer;
import com.kratos.kit.notification.message.NotificationMessageType;
import com.kratos.kit.notification.provider.AlidayuProvider;

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
