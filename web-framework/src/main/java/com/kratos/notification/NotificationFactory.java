package com.kratos.notification;

import com.kratos.notification.alidayu.AlidayuProvider;

/**
 * 消息工厂，负责生产消息
 * @author tang he
 * @since 1.0.0
 */
public class NotificationFactory {
    private NotificationFactory(){}
    public static NotificationProvider createNotification(NotificationType notificationType) {
        switch (notificationType) {
            case ALIDAYU:
                return new AlidayuProvider();
        }
        return null;
    }

    public enum NotificationType {
        ALIDAYU
    }
}
