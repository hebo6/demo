package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Slf4j
public class TextUtils {
    private TextUtils() {
    }

    /**
     * @param sources 如多个参数有相同字段. 会优先使用前面的值
     * @see StringSubstitutor
     */
    public static String replace(String templateString, Object... sources) {
        return replace(templateString, null, sources);
    }

    /**
     * @see StringSubstitutor
     */
    public static String replace(String templateString, Map<String, Object> priorValuesMap, Object... sources) {
        Map<String, Object> valuesMap = new LazyMap(priorValuesMap, sources);
        return new StringSubstitutor(valuesMap).replace(templateString);
    }

    /**
     * 实现懒加载的Map
     */
    private static class LazyMap implements Map<String, Object> {
        private final Map<String, Object> priorMap;
        private final Object[] sources;

        public LazyMap(Map<String, Object> priorMap, Object... sources) {
            this.priorMap = priorMap;
            this.sources = sources;
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean isEmpty() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean containsKey(Object key) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean containsValue(Object value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object get(Object key) {
            if (priorMap != null) {
                Object o = priorMap.get(key);
                if (o != null) {
                    return o;
                }
            }

            if (sources == null) {
                return null;
            }

            for (Object source : sources) {
                if (source == null) {
                    continue;
                }
                Object v = invokeReadMethod(source, key.toString());
                if (v != null) {
                    return v;
                }
            }

            return null;
        }

        private Object invokeReadMethod(Object source, String propertyName) {
            PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(source.getClass(), propertyName);
            if (pd == null) {
                return null;
            }

            Method readMethod = pd.getReadMethod();
            if (readMethod == null) {
                return null;
            }
            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                readMethod.setAccessible(true);
            }

            Object invoke = null;
            try {
                invoke = readMethod.invoke(source);
            } catch (IllegalAccessException | InvocationTargetException e) {
                log.error("唤醒方法发送异常", e);
            }
            return invoke;
        }

        @Override
        public Object put(String key, Object value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object remove(Object key) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void putAll(Map<? extends String, ?> m) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Set<String> keySet() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Collection<Object> values() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Set<Entry<String, Object>> entrySet() {
            throw new UnsupportedOperationException();
        }
    }
}
