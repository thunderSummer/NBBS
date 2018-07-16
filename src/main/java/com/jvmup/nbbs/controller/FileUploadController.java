package com.jvmup.nbbs.controller;

import com.jvmup.nbbs.po.ResponseStyle;
import com.jvmup.nbbs.po.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-15 19:11
 **/
@RestController
public class FileUploadController {
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public ResponseStyle heh(){
        return new ResponseStyle().success(new User());
    }
}
