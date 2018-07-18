package com.jvmup.nbbs.controller;

import com.jvmup.nbbs.po.ResponseStyle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-16 11:31
 **/
@Controller
public class StaticController {

    @RequestMapping("/YouShouldLoginToAccessWeb")
    public String  LoginStatusInvalid(){
        return "login";
    }
}
