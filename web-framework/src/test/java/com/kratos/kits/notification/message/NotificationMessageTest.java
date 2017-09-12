package com.kratos.kits.notification.message;

import org.junit.Assert;
import org.junit.Test;

public class NotificationMessageTest {
    @Test
    public void messageTypeReflection() throws Exception {
        MobileAppBroadcastMessage mobileAppBroadcastMessage = new MobileAppBroadcastMessage();
        SmsBroadcastMessage smsBroadcastMessage = new SmsBroadcastMessage();
        SmsVerifyCodeMessage smsVerifyCodeMessage = new SmsVerifyCodeMessage();
        VoiceVerifyCodeMessage voiceVerifyCodeMessage = new VoiceVerifyCodeMessage();
        Assert.assertArrayEquals(
                new Object[]{
                        NotificationMessageType.MOBILE_APP_BROADCAST,
                        NotificationMessageType.SMS_BROADCAST,
                        NotificationMessageType.SMS_VERIFY_CODE,
                        NotificationMessageType.VOICE_VERIFY_CODE
                },
                new Object[]{
                        mobileAppBroadcastMessage.getMessageType(),
                        smsBroadcastMessage.getMessageType(),
                        smsVerifyCodeMessage.getMessageType(),
                        voiceVerifyCodeMessage.getMessageType()
                });
    }
}
