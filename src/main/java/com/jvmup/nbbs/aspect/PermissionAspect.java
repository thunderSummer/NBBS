package com.jvmup.nbbs.aspect;

import com.jvmup.nbbs.annotation.Permission;
import com.jvmup.nbbs.dao.UserDao;
import com.jvmup.nbbs.exception.LoginStatusInvalidException;
import com.jvmup.nbbs.exception.NoPermissionToGetData;
import com.jvmup.nbbs.exception.UserConsistentFailException;
import com.jvmup.nbbs.po.User;
import com.jvmup.nbbs.service.UserService;
import com.jvmup.nbbs.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;


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
    private UserDao userDao;

    private Logger logger = LogManager.getLogger(this);

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 用于检验用户信息和session 是否一致，第一个参数必须为httpServletRequest，第二个参数可以为User
     * @param pjp pjp呗
     * @return
     */
    @Around(value = "@annotation(com.jvmup.nbbs.annotation.UserConsistent)")
    public Object checkUserConsistent(ProceedingJoinPoint pjp) throws Throwable {
        Object [] args = pjp.getArgs();
        logger.info("正在处理"+pjp.getSignature());
        if (args[0] instanceof HttpServletRequest){
            HttpServletRequest request = (HttpServletRequest) args[0];
            Object o  = request.getSession().getAttribute(StringUtil.loginStatus);
            if (o instanceof User){
                User user = (User) o;
                if (args[1] instanceof User){
                    User user1 = (User) args[1];
                    if (user.getNickname().equals(user1.getNickname())){
                        return pjp.proceed();
                    }else{
                        throw new UserConsistentFailException();
                    }
                }
            }
        }else{
            throw new UserConsistentFailException(pjp.getSignature()+"方法签名出错");
        }
        return pjp.proceed();
    }
    @Around(value = "@annotation(permission)")
    public Object checkPermissionAspect(ProceedingJoinPoint pjp, Permission permission) throws Throwable{
        Object [] args = pjp.getArgs();
        logger.info("正在处理"+pjp.getSignature());

        if (args[0] instanceof HttpServletRequest){
            HttpServletRequest request = (HttpServletRequest) args[0];
            Object o  = request.getSession().getAttribute(StringUtil.loginStatus);
            int type=permission.classify();
            if (type>0) {
                if (o instanceof User){
                    User user = (User) o;
                    switch (type){
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                          //查询版块
                        case 4:
                            //查询分区
                        case 5:
                            //查询管理员

                    }
                }else{
                     throw new LoginStatusInvalidException();
                }
            }
        }
       return pjp.proceed();
    }

//    @Around(value = "execution(com.jvmup.nbbs.po.ResponseStyle com.jvmup.nbbs.controller.ManagerController.*(..))")
//    public Object needAdmin(ProceedingJoinPoint pjp) throws Throwable {
//        Object [] args = pjp.getArgs();
//        logger.info("正在处理"+pjp.getSignature());
//        if (args[0] instanceof HttpServletRequest ){
//            HttpServletRequest request = (HttpServletRequest) args[0];
//            User user = (User) request.getSession().getAttribute(StringUtil.loginStatus);
//            if (user==null){
//                throw  new NoPermissionToGetData("没有权限操作");
//            }
//            if (userDao.isAdmin(user.getId())==1){
//               return pjp.proceed();
//            }else{
//                throw  new NoPermissionToGetData("没有权限操作");
//            }
//        }
//        return pjp.proceed();
//    }
}
