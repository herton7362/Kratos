package com.kratos.kits.notification.config.annotation;

import java.util.LinkedHashMap;

public class AbstractNotificationBuilder implements NotificationBuilder {
    private final LinkedHashMap<Class<? extends NotificationConfigurer>, NotificationConfigurer> configurers = new LinkedHashMap<>();

    @SuppressWarnings("unchecked")
    public <C extends NotificationConfigurer> C getConfigurer(Class<C> clazz) {
        return (C) this.configurers.get(clazz);
    }

    protected <C extends NotificationConfigurerAdapter> C apply(C configurer)
            throws Exception {
        configurer.setBuilder(this);
        this.configurers.put(configurer.getClass(), configurer);
        return configurer;
    }
}
