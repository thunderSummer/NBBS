package com.jvmup.nbbs.exception;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-17 13:58
 **/
public class LoginStatusInvalidException extends Exception {

    public LoginStatusInvalidException(){
        this("用户登录状态无效，请重新登录");
    }
    public LoginStatusInvalidException(String message) {
        super(message);
    }
}
