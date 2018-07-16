package com.jvmup.nbbs.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(JSONValues.class)
public @interface JSONValue {
    String [] exclude();
    String [] include();
}
