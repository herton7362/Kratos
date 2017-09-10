package com.kratos.kit.notification.config.annotation;

import java.util.LinkedHashMap;

public class AbstractNotificationBuilder implements NotificationBuilder {
    private final LinkedHashMap<Class<? extends NotificationConfigurer>, NotificationConfigurer> configurers = new LinkedHashMap<>();

    @SuppressWarnings("unchecked")
    protected <C extends NotificationConfigurer> C getConfigurer(Class<C> clazz) {
        return (C) this.configurers.get(clazz);
    }

    protected <C extends NotificationConfigurerAdapter> C apply(C configurer)
            throws Exception {
        configurer.setBuilder(this);
        this.configurers.put(configurer.getClass(), configurer);
        return configurer;
    }
}
