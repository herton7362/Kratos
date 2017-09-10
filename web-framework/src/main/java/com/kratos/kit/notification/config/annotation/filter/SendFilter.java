package com.kratos.kit.notification.config.annotation.filter;

import com.kratos.common.entity.BaseUser;
import com.kratos.kit.notification.message.NotificationMessageType;

public interface SendFilter {
    boolean isSendAble(BaseUser destUser, NotificationMessageType messageType) throws Exception;
}
