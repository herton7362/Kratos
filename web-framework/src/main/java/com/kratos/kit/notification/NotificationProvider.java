package com.kratos.kit.notification;

import com.kratos.kit.notification.message.NotificationMessage;

public interface NotificationProvider {
    boolean send(NotificationMessage message) throws Exception;
}
