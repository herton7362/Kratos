package com.kratos.kits.notification.config.annotation;

public abstract class NotificationConfigurerAdapter<B extends NotificationBuilder> implements NotificationConfigurer {
    private B notificationBuilder;

    void setBuilder(B builder) {
        this.notificationBuilder = builder;
    }

    private B getBuilder() {
        if (notificationBuilder == null) {
            throw new IllegalStateException("notificationBuilder cannot be null");
        }
        return notificationBuilder;
    }

    public B and() {
        return getBuilder();
    }
}
