package com.jvmup.nbbs.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerEditorMdInterceptor implements HandlerInterceptor {
    private Logger logger = LogManager.getLogger();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//        logger.info(method);
        String path = request.getRequestURI();
        if (path.contains(".css")||path.contains(".js")){
            String newPath = path.substring(path.indexOf('/')+1);
            response.sendRedirect(newPath.substring(newPath.indexOf('/')));
            return false;
        }
        return true;
    }


}
