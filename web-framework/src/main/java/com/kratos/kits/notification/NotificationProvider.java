package com.kratos.kits.notification;

import com.kratos.kits.notification.message.NotificationMessage;

public interface NotificationProvider {
    boolean send(NotificationMessage message) throws Exception;
}
