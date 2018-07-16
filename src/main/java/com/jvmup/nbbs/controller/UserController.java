package com.jvmup.nbbs.controller;

import com.jvmup.nbbs.po.ResponseStyle;
import com.jvmup.nbbs.po.User;
import com.jvmup.nbbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-16 11:32
 **/
@RestController
public class UserController {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping("/user/info/{nickname}")
    public ResponseStyle handleq (@PathVariable String nickname){
        int id = userService.getIdByNickname(nickname);
        return new ResponseStyle().success(userService.getUserInfo(id));
    }

}
