package com.jvmup.nbbs.service;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-16 11:43
 **/
@Service
public class BaseService implements Serializable {
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
