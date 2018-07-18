package com.jvmup.nbbs.exception;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-17 13:43
 **/
public class UserConsistentFailException extends Exception {
    public UserConsistentFailException() {
        this("用户状态信息校验失败");
    }

    public UserConsistentFailException(String message) {
        super(message);
    }
}
