package lsc.jdk.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lsc.jdk.annotation.MySqlParameterField.Type;
import lsc.util.StringTools;

import org.apache.commons.collections4.CollectionUtils;

public class MySqlParameterTools {
    public static void reload(Class<?> clazz, List<? extends MySqlParameter> parameter) throws IllegalArgumentException, IllegalAccessException {
        reload(clazz, parameter, null);
    }

    public static void reload(Class<?> clazz, List<? extends MySqlParameter> parameter, MySqlParameterCallback callBack) throws IllegalArgumentException,
            IllegalAccessException {
        if (clazz == null) {
            return;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        if (CollectionUtils.isNotEmpty(parameter)) {
            for (MySqlParameter p : parameter) {
                map.put(p.getKey(), p.getValue());
            }
        }
        Field[] fields = clazz.getDeclaredFields();
        if (fields != null) {
            for (Field f : fields) {
                Annotation[] ans = f.getAnnotations();
                if (ans != null) {
                    for (Annotation an : ans) {
                        if (an.annotationType().equals(MySqlParameterField.class)) {// 判断是否是自定义注解
                            MySqlParameterField p = (MySqlParameterField) an;
                            String key = p.key();
                            String info = p.info();
                            Type type = p.type();
                            Object old = f.get(clazz);
                            Object now = map.get(key);
                            if (now != null) {
                                f.set(clazz, value(old, now, type));
                            } else if (callBack != null) {
                                callBack.call(key, old, info);
                            }
                        }
                    }
                }
            }
        }
    }

    private static Object value(Object old, Object now, Type type) {
        switch (type) {
            case INTEGER:
                return getInt(old, now);
            case STRING:
                return getString(old, now);
            case FLOAT:
                return getFloat(old, now);
            case LONG:
                return getLong(old, now);
            default:
                return getInt(old, now);
        }
    }

    private static Object getLong(Object old, Object now) {
        long l = StringTools.getInt(old, -1);
        return StringTools.getLong(now, l);
    }

    private static Object getFloat(Object old, Object now) {
        Float i = StringTools.getFloat(old, -1);
        return StringTools.getFloat(now, i);
    }

    private static Object getString(Object old, Object now) {
        return now.toString();
    }

    private static Object getInt(Object old, Object now) {
        int f = StringTools.getInt(old, -1);
        return StringTools.getInt(now, f);
    }
}
