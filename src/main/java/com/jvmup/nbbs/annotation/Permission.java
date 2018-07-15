package com.jvmup.nbbs.annotation;

/**
 * 统一的权限管理注解
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-14 15:48
 **/
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Permission {
    /**
     * @return 返回要求的最小权限，0代表所有人,1代表所有已登录用户，2代表仅自己，3代表版主，4代表管理员
     */
    int classify() default  1;
}
