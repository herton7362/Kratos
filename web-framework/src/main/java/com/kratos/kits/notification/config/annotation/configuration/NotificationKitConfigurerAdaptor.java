package com.kratos.kits.notification.config.annotation.configuration;

import com.kratos.entity.BaseUser;
import com.kratos.kits.notification.config.annotation.builder.NotificationProviders;
import com.kratos.kits.notification.config.annotation.builder.NotificationTypes;
import com.kratos.kits.notification.config.annotation.configurer.SendConfigurer;
import com.kratos.kits.notification.message.NotificationMessageType;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationKitConfigurerAdaptor implements NotificationKitConfigurer {

    @Override
    public void configure(NotificationProviders notificationProvider) throws Exception {

    }

    @Override
    public void configure(NotificationTypes notificationTypes) throws Exception {

    }

    @Override
    public void configure(SendConfigurer sendConfigurer) throws Exception {
        sendConfigurer.setSendFilter((BaseUser destUser, NotificationMessageType messageType) -> true);
    }

}
