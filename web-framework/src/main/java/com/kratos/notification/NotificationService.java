package com.kratos.notification;

/**
 * 消息推送组件接口
 * @author tang he
 * @since 1.0.0
 */
public interface NotificationService {
    boolean pushMessage(final NotificationMessage notificationMessage) throws Exception;
}
