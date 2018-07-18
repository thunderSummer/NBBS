package com.jvmup.nbbs.interceptor;

import com.jvmup.nbbs.annotation.NeedLogin;
import com.jvmup.nbbs.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * 用于 静态html的 校验，哪些网页可以被所有人员访问，哪些网页可以被管理人员访问
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-14 17:43
 **/
public class StaticHtmlCheckInterceptor implements HandlerInterceptor {
    private Logger logger = LogManager.getLogger(this);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        logger.info("正在拦截"+method);
        if (method.isAnnotationPresent(NeedLogin.class)){
            HttpSession session = request.getSession();
            if (session.getAttribute(StringUtil.loginStatus)==null){
                request.getRequestDispatcher("//YouShouldLoginToAccessWeb").forward(request,response);
                return false;
            }
        }
        return true;

    }
}
