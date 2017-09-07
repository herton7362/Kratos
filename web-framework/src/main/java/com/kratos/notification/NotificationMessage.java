package com.kratos.notification;

/**
 * 消息父类
 * @author tang he
 * @since 1.0.0
 */
public abstract class NotificationMessage {
    private String dest;

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }
}
