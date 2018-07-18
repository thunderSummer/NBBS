package com.jvmup.nbbs.aspect;

import com.jvmup.nbbs.exception.NoPermissionToGetData;
import com.jvmup.nbbs.exception.UserNotExistException;
import com.jvmup.nbbs.po.ResponseStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * ProjectName: NBBS
 *  统一的全局异常处理器
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-14 15:10
 **/
@ControllerAdvice
public class ExceptionHandle  {
    private Logger log = LogManager.getLogger(this);
    //    @ResponseBody
//    @ExceptionHandler(Exception.class)
//    public ResponseStyle handleAllException(Exception e){
//        return new ResponseStyle().failure(e.getClass().getName());
//    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(NoPermissionToGetData.class)
    public ResponseStyle handleHttpMessageNotReadableException(
            NoPermissionToGetData e) {
        log.info(e.getMessage());
        return new ResponseStyle().failure(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseStyle handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e) {
        e.printStackTrace();
        return new ResponseStyle().failure(e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNotFoundException(){
        return "exception/404";
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleBadRequest(){
        return "exception/500";
    }

    @ResponseBody
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseStyle handleSQLIntegrityConstraintViolationException(){
        return new ResponseStyle().failure("数据重复异常");
    }

    @ResponseBody
    @ExceptionHandler(UserNotExistException.class)
    public ResponseStyle handleUserNotExist(UserNotExistException u){
        return new ResponseStyle().failure(u.getMessage());
    }
}
