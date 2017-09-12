package com.kratos.kits.notification.config.annotation.builder;

import com.kratos.kits.notification.provider.SmsBroadcastAlidayuProvider;
import com.kratos.kits.notification.provider.SmsVerifyCodeAlidayuProvider;
import com.kratos.kits.notification.provider.VoiceVerifyCodeAlidayuProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.kratos.kits.notification.config.annotation.configurer.*;

public class NotificationTypesTest {
    private NotificationProviders notificationProviders;
    private NotificationTypes notificationTypes;
    @Before
    public void setUp() throws Exception {
        notificationProviders = new NotificationProviders();
        notificationProviders
                .alidayuProvider()
                .setAppId("24495956")
                .setAppSecret("895912d66f8740c478c967b2167019fb")
                .setServerUrl("http://gw.api.taobao.com/router/rest")
                .setSmsFreeSignName("威廉小院")
                .setSmsTemplateCode("SMS_71820364")
                .setSmsType("normal");
        notificationTypes = new NotificationTypes(notificationProviders);
    }

    @Test
    public void getConfigurer() throws Exception {
        Assert.assertArrayEquals(
                new Object[]{
                        notificationTypes.voiceVerifyCode().getClass(),
                        notificationTypes.smsBroadcast().getClass(),
                        notificationTypes.mobileAppBroadcast().getClass(),
                        notificationTypes.smsVerifyCode().getClass()
                },
                new Object[]{
                        VoiceVerifyCodeConfigurer.class,
                        SmsBroadcastConfigurer.class,
                        MobileAppBroadcastConfigurer.class,
                        SmsVerifyCodeConfigurer.class
                }
        );
    }

    @Test
    public void provider() throws Exception {
        Assert.assertArrayEquals(
                new Object[]{
                        notificationTypes.voiceVerifyCode().alidayuProvider().getProvider().getClass(),
                        notificationTypes.smsBroadcast().alidayuProvider().getProvider().getClass(),
                        notificationTypes.smsVerifyCode().alidayuProvider().getProvider().getClass()
                },
                new Object[]{
                        VoiceVerifyCodeAlidayuProvider.class,
                        SmsBroadcastAlidayuProvider.class,
                        SmsVerifyCodeAlidayuProvider.class
                }
        );
    }
}
