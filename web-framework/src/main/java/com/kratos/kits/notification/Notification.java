package com.kratos.kits.notification;

import com.kratos.kits.notification.config.annotation.NotificationBuilder;
import com.kratos.kits.notification.config.annotation.builder.NotificationProviders;
import com.kratos.kits.notification.config.annotation.builder.NotificationTypes;
import com.kratos.kits.notification.config.annotation.configuration.NotificationKitConfigurer;
import com.kratos.kits.notification.config.annotation.configurer.NotificationTypeConfigurer;
import com.kratos.kits.notification.config.annotation.configurer.SendConfigurer;
import com.kratos.kits.notification.message.NotificationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息发送组件，所有发送均会重试3次
 */
@Component
public final class Notification {
    private final NotificationKitConfigurer notificationConfigurer;
    private final NotificationProviders providerConfigurer = new NotificationProviders();
    private final NotificationTypes notificationTypes = new NotificationTypes(providerConfigurer);
    private final SendConfigurer sendConfigurer = new SendConfigurer();
    /**
     * 发送短信验证码
     * @param message 消息类
     * @return 是否发送成功 true 成功/false不成功
     * @throws Exception 处理过程中异常
     */
    public <T extends NotificationMessage> boolean send(T message) throws Exception {
        NotificationProvider<T> provider = getProvider(message);
        return !doFilter(message) || provider.send(message);
    }

    private <T extends NotificationMessage> NotificationProvider<T> getProvider(T message) throws Exception {
        NotificationTypeConfigurer<NotificationBuilder, T> configurer = notificationTypes.getConfigurer(message.getMessageType().getConfigurerClass());
        return configurer.getProvider();
    }

    private boolean doFilter(NotificationMessage message) throws Exception {
        return sendConfigurer.getSendFilter().isSendAble(message.getDestUser(), message.getMessageType());
    }

    private void configure() throws Exception {
        notificationConfigurer.configure(providerConfigurer);
        notificationConfigurer.configure(notificationTypes);
        notificationConfigurer.configure(sendConfigurer);
    }

    @Autowired
    public Notification(NotificationKitConfigurer notificationConfigurer) throws Exception {
        this.notificationConfigurer = notificationConfigurer;
        configure();
    }
}
