package com.kratos.kits;

import com.kratos.kits.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Kits {
    private final Notification notificationKit;

    public Notification notification() {
        return notificationKit;
    }

    @Autowired
    public Kits(Notification notificationKit) {
        this.notificationKit = notificationKit;
    }
}
