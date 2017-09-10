package com.kratos.kit.notification.config.annotation.configuration;

import com.kratos.kit.notification.config.annotation.builder.NotificationProviders;
import com.kratos.kit.notification.config.annotation.configurer.SendConfigurer;
import com.kratos.kit.notification.config.annotation.filter.SendFilter;

/**
 * 消息配置
 * @author tang he
 * @since 1.0.0
 */
public interface NotificationKitConfigurer {
    /**
     * 消息提供商配置
     * @param notificationProvider 消息提供商配置类
     * @throws Exception 异常
     */
    void configure(NotificationProviders notificationProvider) throws Exception;

    /**
     * 有关发送方面的配置
     * @param sendConfigurer 可以配置消息过滤器 {@link SendFilter}
     * @throws Exception 异常
     */
    void configure(SendConfigurer sendConfigurer) throws Exception;
}
