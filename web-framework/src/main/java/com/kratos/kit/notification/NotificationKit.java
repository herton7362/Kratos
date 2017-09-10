package com.kratos.kit.notification;

import com.kratos.kit.notification.config.annotation.builder.NotificationProviders;
import com.kratos.kit.notification.config.annotation.configuration.NotificationKitConfigurer;
import com.kratos.kit.notification.config.annotation.configurer.SendConfigurer;
import com.kratos.kit.notification.message.NotificationMessage;
import com.kratos.kit.notification.message.NotificationMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息发送组件，所有发送均会重试3次
 */
@Component
public final class NotificationKit {
    private final NotificationKitConfigurer notificationConfigurer;
    private final NotificationProviders providerConfigurer = new NotificationProviders();
    private final SendConfigurer sendConfigurer = new SendConfigurer();
    /**
     * 发送短信验证码
     * @param message 消息类
     * @return 是否发送成功 true 成功/false不成功
     * @throws Exception 处理过程中异常
     */
    public boolean send(NotificationMessage message) throws Exception {
        // TODO 改为从@Configuration中获取，而非实例化NotificationConfigurerAdaptor
        NotificationProvider provider = getProvider(message.getMessageType());
        return !doFilter(message) || provider.send(message);
    }

    private NotificationProvider getProvider(NotificationMessageType messageType) throws Exception {
        return (NotificationMessage message) -> true;
    }

    private boolean doFilter(NotificationMessage message) throws Exception {
        return sendConfigurer.getSendFilter().isSendAble(message.getDestUser(), message.getMessageType());
    }

    private void configure() throws Exception {
        notificationConfigurer.configure(providerConfigurer);
    }

    @Autowired
    public NotificationKit(NotificationKitConfigurer notificationConfigurer) throws Exception {
        this.notificationConfigurer = notificationConfigurer;
        configure();
    }
}
