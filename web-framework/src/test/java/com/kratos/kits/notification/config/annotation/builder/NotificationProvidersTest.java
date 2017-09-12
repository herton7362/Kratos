package com.kratos.kits.notification.config.annotation.builder;

import org.junit.Assert;
import org.junit.Test;
import com.kratos.kits.notification.config.annotation.configurer.AlidayuProviderConfigurer;

public class NotificationProvidersTest {
    private NotificationProviders notificationProviders = new NotificationProviders();
    @Test
    public void alidayuProvider() throws Exception {
        Assert.assertEquals(notificationProviders.alidayuProvider().getClass(), AlidayuProviderConfigurer.class);
    }

    @Test
    public void getConfigurer() throws Exception {
        Assert.assertEquals(notificationProviders.alidayuProvider(), notificationProviders.getConfigurer(AlidayuProviderConfigurer.class));
    }

    @Test
    public void setAlidayuParam() throws Exception {
        notificationProviders
                .alidayuProvider()
                .setAppId("24495956")
                .setAppSecret("895912d66f8740c478c967b2167019fb")
                .setServerUrl("http://gw.api.taobao.com/router/rest")
                .setSmsFreeSignName("威廉小院")
                .setSmsTemplateCode("SMS_71820364")
                .setSmsType("normal");
        Assert.assertArrayEquals(
                new String[]{
                        "24495956",
                        "895912d66f8740c478c967b2167019fb",
                        "http://gw.api.taobao.com/router/rest",
                        "威廉小院",
                        "SMS_71820364",
                        "normal"
                }
                ,new String[]{
                        notificationProviders.alidayuProvider().getAppId(),
                        notificationProviders.alidayuProvider().getAppSecret(),
                        notificationProviders.alidayuProvider().getServerUrl(),
                        notificationProviders.alidayuProvider().getSmsFreeSignName(),
                        notificationProviders.alidayuProvider().getSmsTemplateCode(),
                        notificationProviders.alidayuProvider().getSmsType()
                });
    }
}
