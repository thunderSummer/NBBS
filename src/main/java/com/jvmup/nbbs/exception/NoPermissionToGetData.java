package com.jvmup.nbbs.exception;

import org.springframework.dao.DataAccessException;

import javax.validation.constraints.NotNull;

/**
 * ProjectName: NBBS
 *当用户没有权限进行某些操作的时候，将会抛出这个异常
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-14 15:23
 **/
public class NoPermissionToGetData extends DataAccessException {
    public NoPermissionToGetData (String msg){
        super(msg);
    }

    public NoPermissionToGetData(String msg, @NotNull Exception cause){
        super(msg,cause);
    }
}
