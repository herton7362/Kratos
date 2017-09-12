package com.kratos.kits.notification.config.annotation.configuration;

import com.kratos.kits.notification.config.annotation.builder.NotificationProviders;
import com.kratos.kits.notification.config.annotation.builder.NotificationTypes;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.util.Collections;
import java.util.List;


@RunWith(Parameterized.class)
public class NotificationKitConfigurerTest {
    private AnnotationConfigWebApplicationContext context;
    private Class<?>[] resources;

    @Parameters
    public static List<Object[]> parameters() {
        return Collections.singletonList(new Object[] { BeanCreationException.class, new Class<?>[] { NotificationKitConfigurer.class } });
    }

    public NotificationKitConfigurerTest(Class<? extends Exception> error, Class<?>... resource) {
        this.resources = resource;
        context = new AnnotationConfigWebApplicationContext();
        context.setServletContext(new MockServletContext());
        context.register(resource);
    }

    @After
    public void close() {
        if (context != null) {
            context.close();
        }
    }

    @Test
    public void testDefaults() {
        context.refresh();
        for (Class<?> resource : resources) {
            if (Runnable.class.isAssignableFrom(resource)) {
                ((Runnable) context.getBean(resource)).run();
            }
        }
    }
    @Configuration
    public static class NotificationKitConfigurer extends NotificationKitConfigurerAdaptor implements Runnable {
        @Autowired
        private NotificationProviders notificationProvider;
        @Override
        public void configure(NotificationProviders notificationProvider) throws Exception {
            notificationProvider.alidayuProvider();
        }

        @Override
        public void configure(NotificationTypes notificationTypes) throws Exception {
            notificationTypes.smsVerifyCode().alidayuProvider();
        }

        @Override
        public void run() {
            System.out.println(notificationProvider);
        }
    }
}
