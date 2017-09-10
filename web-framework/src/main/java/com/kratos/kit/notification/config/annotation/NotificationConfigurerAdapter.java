package com.kratos.kit.notification.config.annotation;

public abstract class NotificationConfigurerAdapter implements NotificationConfigurer {
    private NotificationBuilder notificationBuilder;

    public void setBuilder(NotificationBuilder builder) {
        this.notificationBuilder = builder;
    }

    public NotificationBuilder getBuilder() {
        if (notificationBuilder == null) {
            throw new IllegalStateException("notificationBuilder cannot be null");
        }
        return notificationBuilder;
    }

    public NotificationBuilder and() {
        return getBuilder();
    }
}
