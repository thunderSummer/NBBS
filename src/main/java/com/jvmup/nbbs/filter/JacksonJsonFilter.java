package com.jvmup.nbbs.filter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

import java.util.*;

/**
 * Jackson自定义的属性过滤器,如果某个类的某个方法被同时设置了include和exclude，则include将会起效
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-15 14:54
 **/
public class JacksonJsonFilter extends FilterProvider {
    //在序列化过程中中仅包括的属性
    private Map<Class<?>,Set<String>> includeMap = new HashMap<>();
    //在序列化过程中不包括的属性
    private Map<Class<?>,Set<String>> excludeMap = new HashMap<>();

    public void include(Class<?> type, String... fields) {
        addToMap(includeMap, type, fields);
    }

    public void exclude(Class<?> type, String... fields) {
        addToMap(excludeMap, type, fields);
    }
    private void addToMap(Map<Class<?>, Set<String>> map, Class<?> type, String[] fields) {
        //如果多次添加相同类的 include(exclude)属性，那么将在保留原有已经添加的属性
        Set<String> fieldSet = map.getOrDefault(type, new HashSet<>());
        fieldSet.addAll(Arrays.asList(fields));
        map.put(type, fieldSet);
    }

    //已经过期的方法，调用将抛出异常
    @Override
    public BeanPropertyFilter findFilter(Object filterId) {
        throw new UnsupportedOperationException("Access to deprecated filters not supported");
    }
    //重写定位属性的过滤器的方法,仍然基于默认的SimpleBeanPropertyFilter，
    @Override
    public PropertyFilter findPropertyFilter(Object filterId, Object valueToFilter) {

        return new SimpleBeanPropertyFilter() {

            @Override
            public void serializeAsField(Object pojo, JsonGenerator gen, SerializerProvider prov, PropertyWriter writer)
                    throws Exception {
                if (apply(pojo.getClass(), writer.getName())) {
                    writer.serializeAsField(pojo, gen, prov);
                } else if (!gen.canOmitFields()) {
                    writer.serializeAsOmittedField(pojo, gen, prov);
                }
            }
        };
    }

    /**
     * 多段
     * @param type
     * @param name
     * @return
     */

    public boolean apply(Class<?> type, String name) {
        Set<String> includeFields = includeMap.get(type);
        Set<String> excludeFields = excludeMap.get(type);
        if (includeFields != null && includeFields.contains(name)) {
            return true;
        } else if (excludeFields != null && !excludeFields.contains(name)) {
            return true;
        } else if (includeFields == null && excludeFields == null) {
            return true;
        }
        return false;
    }
}
