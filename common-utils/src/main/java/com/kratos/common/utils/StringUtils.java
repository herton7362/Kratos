package com.kratos.common.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by He on 2017/4/28.
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {
    public static String parse(String source, Map<String, Object> params) throws NoSuchFieldException, IllegalAccessException {
        Set<Map.Entry<String, Object>> entries = params.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            if(isPrimitive(entry.getValue().getClass()) || entry.getValue() instanceof String) {
                source = source.replaceAll("\\{"+entry.getKey()+"\\}", String.valueOf(entry.getValue()));
            } else if(entry.getValue() instanceof HashMap) {
                source = parse(source, (Map<String, Object>) entry.getValue());
            } else {
                Field[] fields = ReflectionUtils.getDeclaredFields(entry.getValue().getClass());
                for (Field field : fields) {
                    source = source.replaceAll("\\{"+entry.getKey()+"."+field.getName()+"\\}", String.valueOf(ReflectionUtils.getFieldValue(entry.getValue(), field)));
                }
            }
        }
        return source;
    }

    private static boolean isPrimitive(Class clazz) throws NoSuchFieldException, IllegalAccessException {
        Field filed = ReflectionUtils.getDeclaredField(clazz, "TYPE");
        if(!clazz.isPrimitive() && filed != null) {
            return ((Class)filed.get(null)).isPrimitive();
        }
        return clazz.isPrimitive();
    }

    public static String toFirstCharUpperCase(String source) {
        char[] chars = source.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return String.valueOf(chars);
    }
}
