package com.jvmup.nbbs.interceptor;

import com.jvmup.nbbs.annotation.UserVerify;
import com.jvmup.nbbs.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * 用于核实该操作是否为本人操作，例如修改头像，修改密码，回复帖子,如果登录状态失效，将会被转发到/loginStatusInvalid
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-14 17:43
 **/
public class UserVerifyInterceptor implements HandlerInterceptor {
    private Logger logger = LogManager.getLogger(this);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        logger.info("正在拦截"+method);
        if (method.isAnnotationPresent(UserVerify.class)){
            HttpSession session = request.getSession();
            if (session.getAttribute(StringUtil.loginStatus)==null){
                request.getRequestDispatcher("/loginStatusInvalid").forward(request,response);
                return false;
            }
        }
        return true;

    }
}
