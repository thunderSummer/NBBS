package com.jvmup.nbbs.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jvmup.nbbs.annotation.JSON;
import com.jvmup.nbbs.filter.JacksonJsonFilter;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-15 19:00
 **/
public class CustomerJsonSerializer {


    ObjectMapper mapper = new ObjectMapper();
    JacksonJsonFilter jacksonFilter = new JacksonJsonFilter();

    /**
     * @param clazz target type
     * @param include include fields
     * @param exclude exclude fields
     */
    public void filter(Class<?> clazz, String[] include, String[] exclude) {
        if (clazz == null) return;
            jacksonFilter.include(clazz, include);
            jacksonFilter.exclude(clazz, exclude);
        mapper.addMixIn(clazz, jacksonFilter.getClass());
    }

    public String toJson(Object object) throws JsonProcessingException {
        mapper.setFilterProvider(jacksonFilter);
        return mapper.writeValueAsString(object);
    }
    public void filter(JSON json) {
        this.filter(json.type(), json.include(), json.exclude());
    }
}

