package com.kratos.notification.alidayu;

import com.kratos.notification.NotificationMessage;
import com.kratos.notification.NotificationProvider;

/**
 * 阿里大鱼消息推送实现
 * @author tang he
 * @since 1.0.0
 */
public class AlidayuProvider extends NotificationProvider {
    @Override
    public boolean pushMessage(NotificationMessage notificationMessage) throws Exception {
        return false;
    }
}
