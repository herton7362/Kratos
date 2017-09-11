package com.kratos.kits.notification.config.annotation.filter;

import com.kratos.entity.BaseUser;
import com.kratos.kits.notification.message.NotificationMessageType;

public interface SendFilter {
    boolean isSendAble(BaseUser destUser, NotificationMessageType messageType) throws Exception;
}
