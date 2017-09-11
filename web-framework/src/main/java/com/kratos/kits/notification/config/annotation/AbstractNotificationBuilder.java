package com.kratos.kits.notification.config.annotation;

import java.util.LinkedHashMap;

public class AbstractNotificationBuilder<B extends NotificationBuilder> implements NotificationBuilder<B> {
    private final LinkedHashMap<Class<? extends NotificationConfigurer>, NotificationConfigurer> configurers = new LinkedHashMap<>();

    @SuppressWarnings("unchecked")
    public <C extends NotificationConfigurer> C getConfigurer(Class<C> clazz) {
        return (C) this.configurers.get(clazz);
    }

    @SuppressWarnings("unchecked")
    private <C extends NotificationConfigurerAdapter> C apply(C configurer)
            throws Exception {
        configurer.setBuilder(this);
        this.configurers.put(configurer.getClass(), configurer);
        return configurer;
    }

    @SuppressWarnings("unchecked")
    protected <C extends NotificationConfigurerAdapter> C getOrApply(
            C configurer) throws Exception {
        C existingConfig =  (C) getConfigurer(configurer.getClass());
        if (existingConfig != null) {
            return existingConfig;
        }
        return apply(configurer);
    }
}
