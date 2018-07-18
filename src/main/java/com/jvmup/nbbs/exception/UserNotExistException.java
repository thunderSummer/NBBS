package com.jvmup.nbbs.exception;

/**
 * 用户不存在异常
 * 使用时，请一定将不存在的原因写上，例如，登录失败，用户信息不存在
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-17 12:02
 **/
public class UserNotExistException extends Exception{
    public UserNotExistException() {
    }

    public UserNotExistException(String message) {
        super(message);
    }
}
