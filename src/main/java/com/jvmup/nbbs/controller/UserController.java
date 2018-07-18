package com.jvmup.nbbs.controller;

import com.jvmup.nbbs.annotation.NeedLogin;
import com.jvmup.nbbs.annotation.UserConsistent;
import com.jvmup.nbbs.po.Post;
import com.jvmup.nbbs.po.ResponseStyle;
import com.jvmup.nbbs.po.User;
import com.jvmup.nbbs.service.UserService;
import com.jvmup.nbbs.util.StringUtil;
import com.jvmup.nbbs.util.TimeUtil;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    /**
     * 用户注册功能
     * @param param 接受请求体传参
     * @param request 绑定request对象
     * @return
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResponseStyle registerUser(@Valid @RequestBody Param param, HttpServletRequest request){
        HttpSession session = request.getSession();
        String result = (String) session.getAttribute(StringUtil.vCode);
        if (param.vCode.equals(result.split("#")[0])){
            if(TimeUtil.cmpTime(result.split("#")[1])){
                userService.register(param.user);
                session.setAttribute(StringUtil.loginStatus,param.user);
                return new ResponseStyle().success();
            }
            return new ResponseStyle().failure("验证码时间过期");
        }
        return new ResponseStyle().failure("验证码错误");
    }
    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public ResponseStyle userLogin(@RequestBody User user,HttpServletRequest request){
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute(StringUtil.loginStatus);
        if (sessionUser!=null){
            if (user.getPassword().equals(sessionUser.getPassword())&&user.getEmail().equals(sessionUser.getEmail())){
                return new ResponseStyle().failure("你已经成功登录，无需重复登录");
            }else{
                return new ResponseStyle().failure("无法同时登录两个用户，请先注销");
            }
        }
        user.setNickname(userService.getNicknameByEmail(user.getEmail()));
        session.setAttribute(StringUtil.loginStatus,user);
        return new ResponseStyle().success();
    }
    @UserConsistent
    @RequestMapping(value = "/user/logout",method = RequestMethod.POST)
    public ResponseStyle userLogout(HttpServletRequest request,@RequestBody User user){
        request.getSession().removeAttribute(StringUtil.loginStatus);
        return new ResponseStyle().success();
    }

    @RequestMapping(value = "/user/info",method = RequestMethod.POST)
    public ResponseStyle getOwnUserInfo(HttpServletRequest request){
        return new ResponseStyle().success(userService.getUserInfo(1));
    }



class Param{
        @NotNull
    private String vCode;
        @NotNull
    private User user;

    public String getvCode() {
        return vCode;
    }

    public User getUser() {
        return user;
    }

    public void setvCode(String vCode) {
        this.vCode = vCode;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

}
