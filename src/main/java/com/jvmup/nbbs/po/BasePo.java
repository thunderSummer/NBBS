package com.jvmup.nbbs.po;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-14 21:26
 **/
public class BasePo implements Serializable {
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
