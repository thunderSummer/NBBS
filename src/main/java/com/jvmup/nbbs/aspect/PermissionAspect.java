package com.jvmup.nbbs.aspect;

import com.jvmup.nbbs.annotation.Permission;
import com.jvmup.nbbs.po.User;
import com.jvmup.nbbs.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import javax.servlet.http.HttpServletRequest;


/**
 * 切面类，用于处理，对操作有要求的用户操作，例如,删帖，登录，
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-14 18:01
 **/
@Aspect
public class PermissionAspect {
    private Logger logger = LogManager.getLogger(this);
    @Around(value = "@annotation(permission)")
    public Object checkPermissionAspect(ProceedingJoinPoint pjp, Permission permission) throws Throwable{
        Object [] args = pjp.getArgs();
        logger.info("正在处理"+pjp.getSignature());

        if (args[0] instanceof HttpServletRequest){
            HttpServletRequest request = (HttpServletRequest) args[0];
            Object o  = request.getSession().getAttribute(StringUtil.loginStatus);
            if (o instanceof User){
                User user = (User) o;

            }

        }

       return pjp.proceed();

    }
}
