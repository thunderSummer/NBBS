package com.jvmup.nbbs.controller;

import com.jvmup.nbbs.annotation.NeedLogin;
import com.jvmup.nbbs.annotation.UserConsistent;
import com.jvmup.nbbs.po.Post;
import com.jvmup.nbbs.po.ResponseStyle;
import com.jvmup.nbbs.po.User;
import com.jvmup.nbbs.service.UserService;
import com.jvmup.nbbs.util.StringUtil;
import com.jvmup.nbbs.util.TimeUtil;
import com.sun.org.apache.regexp.internal.RE;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-16 11:32
 **/
@RestController
public class UserController extends BaseController{
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
    public ResponseStyle registerUser(@RequestBody Param param, HttpServletRequest request){
        HttpSession session = request.getSession();
        String result = (String) session.getAttribute(StringUtil.vCode);
//        if (result==null||result.equals(""))
//            return new ResponseStyle().failure("用户信息获取失败");
        if (param.vCode.equals(result.split("#")[0])){
                userService.register(param.user);
                session.setAttribute(StringUtil.loginStatus,param.user);
                return new ResponseStyle().success();
        }
        return new ResponseStyle().failure("验证码错误");
    }

    /**
     *
     * @param user 传入user参数
     * @return
     */
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
        user.setId(userService.getIdByEmail(user.getEmail()));
        session.setAttribute(StringUtil.loginStatus,user);
        return new ResponseStyle().success();
    }
    @UserConsistent
    @RequestMapping(value = "/user/logout",method = RequestMethod.POST)
    public ResponseStyle userLogout(HttpServletRequest request,@RequestBody User user){
        request.getSession().removeAttribute(StringUtil.loginStatus);
        return new ResponseStyle().success();
    }

    /**
     * 获取自己的信息，
     * @param request
     * @return
     */
    @NeedLogin
    @RequestMapping(value = "/user/info",method = RequestMethod.GET)
    public ResponseStyle getOwnerUserInfo(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(StringUtil.loginStatus);
        return new ResponseStyle().success(userService.getUserInfo(user.getId()));
    }

//    @NeedLogin
    @RequestMapping(value = "/user/info/{nickname}",method = RequestMethod.GET)
    public ResponseStyle getOtherInfo(HttpServletRequest request,@PathVariable String nickname){
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute(StringUtil.loginStatus);
        /*if (userService.inBlackList(user.getNickname(),nickname)){
            return new ResponseStyle().success();
        }*/
        return new ResponseStyle().success(userService.getOtherInfo(userService.getIdByNickname(nickname)));
    }

    /**
     * 重置密码时使用，包括忘记密码和更改密码
     * @return
     */
    @RequestMapping(value = "/user/password",method = RequestMethod.PUT)
    public ResponseStyle resetPassword(HttpServletRequest request,@RequestBody Param param){
        HttpSession session = request.getSession();
        String result = (String) session.getAttribute(StringUtil.vCode);
        if (param.vCode.equals(result.split("#")[0])){
            if(TimeUtil.cmpTime(result.split("#")[1])){
                User user = (User) session.getAttribute(StringUtil.loginStatus);
                param.user.setId(user.getId());
                userService.updatePassword(param.user);
                session.setAttribute(StringUtil.loginStatus,param.user);
                return new ResponseStyle().success("密码更改成功");
            }
            return new ResponseStyle().failure("验证码时间过期");
        }
        return new ResponseStyle().failure("验证码错误");
    }

    @RequestMapping(value = "/user/status",method = RequestMethod.POST)
    public ResponseStyle getUserStatus(HttpServletRequest request,@RequestBody Param1 param1){
        HttpSession httpSession = request.getSession();
        Map<String,Boolean> map = new HashMap<>();
        User user = (User) httpSession.getAttribute(StringUtil.loginStatus);
        if (user==null||user.getId()==0){
            map.put("user",false);
        }else{
            map.put("user",true);
            userService.getUserStatus(user.getId(),param1.getPartitionId(),param1.getSectionId(),map);
        }
        return new ResponseStyle().success(map);

    }

    @RequestMapping(value = "/user/FBF/num",method = RequestMethod.POST)
    public ResponseStyle getUserFansOrBlackOrFollowNum(@RequestBody User user){
        Map<String,Integer> map = new HashMap<>();
        map.put("fans",userService.getFans(user.getNickname()).size());
        map.put("black",userService.getBlack(user.getNickname()).size());
        map.put("follow",userService.getFollows(user.getNickname()).size());
        return new ResponseStyle().success(map);
    }
    @NeedLogin
    @RequestMapping(value = "/user/fans",method = RequestMethod.GET)
    public ResponseStyle getUserFans(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(StringUtil.loginStatus);
        return new ResponseStyle().success(userService.getFans(user.getNickname()));
    }
    @NeedLogin
    @RequestMapping(value = "/user/black",method = RequestMethod.GET)
    public ResponseStyle getUserBlack(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(StringUtil.loginStatus);
        return new ResponseStyle().success(userService.getBlack(user.getNickname()));
    }
    @NeedLogin
    @RequestMapping(value = "/user/follow",method = RequestMethod.GET)
    public ResponseStyle getUserFollow(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(StringUtil.loginStatus);
        return new ResponseStyle().success(userService.getFollows(user.getNickname()));
    }

    @NeedLogin
    @RequestMapping(value = "/user/follow",method = RequestMethod.POST)
    public ResponseStyle addFollow(HttpServletRequest request,@RequestBody User user1){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(StringUtil.loginStatus);
        userService.addFollow(user.getNickname(),user1.getNickname());
        return new ResponseStyle().success();
    }
    @NeedLogin
    @RequestMapping(value = "/user/black",method = RequestMethod.POST)
    public ResponseStyle addBlack(HttpServletRequest request,@RequestBody User user1){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(StringUtil.loginStatus);
        userService.addBlack(user.getNickname(),user1.getNickname());
        return new ResponseStyle().success();
    }
    @NeedLogin
    @RequestMapping(value = "/user/follow",method = RequestMethod.DELETE)
    public ResponseStyle deleteFollow(HttpServletRequest request,@RequestBody User user1){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(StringUtil.loginStatus);
        userService.removeFollow(user.getNickname(),user1.getNickname());
        return new ResponseStyle().success();
    }
    @NeedLogin
    @RequestMapping(value = "/user/black",method = RequestMethod.DELETE)
    public ResponseStyle deleteBlack(HttpServletRequest request,@RequestBody User user1){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(StringUtil.loginStatus);
        userService.removeBlack(user.getNickname(),user1.getNickname());
        return new ResponseStyle().success();
    }
    @NeedLogin
    @RequestMapping(value = "/user/fans",method = RequestMethod.DELETE)
    public ResponseStyle deleteFans(HttpServletRequest request,@RequestBody User user1){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(StringUtil.loginStatus);
        userService.removeFans(user.getNickname(),user1.getNickname());
        return new ResponseStyle().success();
    }

    @NeedLogin
    @RequestMapping(value = "/user/isFollow/{nickname}",method = RequestMethod.GET)
    public ResponseStyle isFollow(HttpServletRequest request,@PathVariable String nickname){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(StringUtil.loginStatus);
        return new ResponseStyle().success(userService.isFans(user.getId(),nickname));
    }
    @NeedLogin
    @RequestMapping(value = "/user/isFans/{nickname}",method = RequestMethod.POST)
    public ResponseStyle isFan(@PathVariable String nickname,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(StringUtil.loginStatus);
        return new ResponseStyle().success(userService.isFollow(user.getId(),nickname));
    }

    @NeedLogin
    @RequestMapping(value = "/user/isBlack/{nickname}",method = RequestMethod.POST)
    public ResponseStyle isBlack(@PathVariable String nickname,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(StringUtil.loginStatus);
        return new ResponseStyle().success(userService.isBlack(nickname,user.getNickname()));
    }

    @NeedLogin
    @RequestMapping(value = "/user/email",method = RequestMethod.PUT)
    public ResponseStyle updateEmail(@RequestBody Param param,HttpServletRequest request){
        HttpSession session = request.getSession();
        String result = (String) session.getAttribute(StringUtil.vCode);
        if (param.vCode.equals(result.split("#")[0])){
            if(TimeUtil.cmpTime(result.split("#")[1])){
                User user = (User) session.getAttribute(StringUtil.loginStatus);
                param.user.setId(user.getId());
                userService.updateMail(param.user);
                session.setAttribute(StringUtil.loginStatus,param.user);
                return new ResponseStyle().success("邮箱更新成功");
            }
            return new ResponseStyle().failure("验证码时间过期");
        }
        return new ResponseStyle().failure("验证码错误");
    }
    @NeedLogin
    @RequestMapping(value = "/user/info",method = RequestMethod.PUT)
    public ResponseStyle updateUserInfo(@RequestBody User user1,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(StringUtil.loginStatus);
        user1.setId(user.getId());
        userService.updateUserInfo(user1);
        return new ResponseStyle().success("信息更新成功");
    }
    @RequestMapping(value = "/user/status",method = RequestMethod.PUT)
    public ResponseStyle updateStatus(@RequestBody User user){
        user.setId(userService.getIdByNickname(user.getNickname()));
        if (userService.isAdmin(user.getId())){
            userService.updateStatus(user);
            return new ResponseStyle().success();
        }
        return new ResponseStyle().failure("没有权限");
    }


    static class Param{
        @NotNull
    private String vCode;
        @NotNull
    private User user;

    public Param() {
    }

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
    static class Param1{
        private int sectionId;
        private int partitionId;


        public Param1() {
        }

        public int getSectionId() {
            return sectionId;
        }

        public void setSectionId(int sectionId) {
            this.sectionId = sectionId;
        }

        public int getPartitionId() {
            return partitionId;
        }

        public void setPartitionId(int partitionId) {
            this.partitionId = partitionId;
        }
    }
}
