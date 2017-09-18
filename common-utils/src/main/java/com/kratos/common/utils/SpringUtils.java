package com.kratos.common.utils;

import org.springframework.context.ApplicationContext;

public class SpringUtils {
    private static ApplicationContext applicationContext = null;

    public static void setApplicationContext(ApplicationContext applicationContext){
        if(SpringUtils.applicationContext == null){
            SpringUtils.applicationContext  = applicationContext;
        }

    }

    /**
     * 获取applicationContext
     *
     * @return {@link ApplicationContext}
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name bean的名称
     * @return 实体
     */
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);

    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz bean的类
     * @param <T> bean类型
     * @return bean
     */
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name bean的名称
     * @param clazz bean的类
     * @param <T> bean类型
     * @return bean
     */
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }

}
