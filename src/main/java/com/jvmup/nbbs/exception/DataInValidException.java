package com.jvmup.nbbs.exception;

import org.springframework.dao.DataAccessException;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-19 20:46
 **/
public class DataInValidException extends Exception {
    public DataInValidException(){
        this("数据已经失效");
    }

    public DataInValidException(String message) {
        super(message);
    }
}
