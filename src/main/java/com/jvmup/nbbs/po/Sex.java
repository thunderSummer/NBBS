package com.jvmup.nbbs.po;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Sex {
    MALE(1,"男"),FEMALE(2,"女");
    private String message;
    private int id;
    Sex(int id,String message) {
        this.message = message;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    @JsonValue
    public String getMessage() {
        return message;
    }
    public static Sex getId(int id){
        if (id==1)
            return MALE;
        if (id==2)
            return FEMALE;
        return null;
    }
}
