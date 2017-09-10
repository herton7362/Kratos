package com.kratos.kit.notification.config.annotation.builder;

import com.kratos.kit.notification.config.annotation.AbstractNotificationBuilder;
import com.kratos.kit.notification.config.annotation.NotificationConfigurerAdapter;
import com.kratos.kit.notification.config.annotation.configurer.SmsVerifyCodeConfigurer;

/**
 * 消息提供商构建
 * @author tang he
 * @since 1.0.0
 */
public class NotificationProviders extends AbstractNotificationBuilder {

    public SmsVerifyCodeConfigurer smsVerifyCode() throws Exception {
        return getOrApply(new SmsVerifyCodeConfigurer());
    }

    @SuppressWarnings("unchecked")
    private <C extends NotificationConfigurerAdapter> C getOrApply(
            C configurer) throws Exception {
        C existingConfig =  (C) getConfigurer(configurer.getClass());
        if (existingConfig != null) {
            return existingConfig;
        }
        return apply(configurer);
    }
}
