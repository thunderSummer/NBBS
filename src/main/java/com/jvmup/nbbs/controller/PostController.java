package com.jvmup.nbbs.controller;

import com.jvmup.nbbs.annotation.NeedLogin;
import com.jvmup.nbbs.annotation.Permission;
import com.jvmup.nbbs.annotation.UserConsistent;
import com.jvmup.nbbs.po.Post;
import com.jvmup.nbbs.po.ResponseStyle;
import com.jvmup.nbbs.po.User;
import com.jvmup.nbbs.service.PostService;

import com.jvmup.nbbs.service.UserService;
import com.jvmup.nbbs.util.PageParam;
import com.jvmup.nbbs.util.StringUtil;
import org.apache.ibatis.annotations.ResultMap;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestScope;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Past;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-16 11:32
 * @editor gzp
 * edit by gzp on -2018-7-17 16:42
 **/
@RestController
public class PostController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    private PostService postService;


    @RequestMapping(value = "/post/topping", method = RequestMethod.GET)
    public ResponseStyle getToppingPost(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(StringUtil.loginStatus);
        String nickname = "";
        if(user!=null){
            nickname=user.getNickname();
        }
        return new ResponseStyle().success(postService.getToppingPost(nickname));
    }
    @RequestMapping(value = "/post/hot",method = RequestMethod.GET)
    public ResponseStyle getHotPost(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(StringUtil.loginStatus);
        String nickname = "";
        if(user!=null){
            nickname=user.getNickname();
        }
        return new ResponseStyle().success(postService.getHotPost(nickname));
    }
    @NeedLogin
    @RequestMapping(value = "/post",method = RequestMethod.POST)
    public ResponseStyle addPost(@RequestBody Post post,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(StringUtil.loginStatus);
        post.getUser().setId(user.getId());
        post.getUser().setId(6);
        postService.addPost(post);
        return new ResponseStyle().success();
    }

    @RequestMapping(value = "/post/{id}",method = RequestMethod.DELETE)
    public ResponseStyle deletePost(HttpServletRequest request,@PathVariable int id) throws Exception{
        HttpSession session = request.getSession();
        User user1 = (User) session.getAttribute(StringUtil.loginStatus);
        postService.deletePost(id,user1.getId());
        return new ResponseStyle().success();
    }

    @RequestMapping(value = "/post/section",method = RequestMethod.PUT)
    public ResponseStyle changeSection(Post post,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(StringUtil.loginStatus);
        postService.changeType(user.getId(),post.getId(),post.getSection().getId());
        return new ResponseStyle().success();
    }
    @RequestMapping(value = "/post/top",method = RequestMethod.PUT)
    public ResponseStyle toppingType(HttpServletRequest request,@RequestBody Post post){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(StringUtil.loginStatus);
        postService.toppingPost(post.getId(),user.getId(),post.getTopping());
        return new ResponseStyle().success();
    }

    @RequestMapping(value = "/post/pick",method = RequestMethod.POST)
    public ResponseStyle pickPost(HttpServletRequest request,@RequestBody Post post){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(StringUtil.loginStatus);
        postService.praise(post,user.getId());
        return new ResponseStyle().success();
    }

    @RequestMapping(value = "/post/collection",method = RequestMethod.POST)
    public ResponseStyle addCollection(HttpServletRequest request,@RequestBody Post post){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(StringUtil.loginStatus);
        postService.collect(post.getId(),user.getId());
        return new ResponseStyle().success();
    }

    @RequestMapping(value = "/post/report",method = RequestMethod.POST)
    public ResponseStyle report(HttpServletRequest request,@RequestBody Param param){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(StringUtil.loginStatus);
        postService.Report(user.getId(),param.postId,param.getReason());
        return new ResponseStyle().success();
    }
    @RequestMapping(value = "/post/user/{nickname}",method = RequestMethod.GET)
    public ResponseStyle getUserAllPost(@PathVariable String nickname){

        int id = userService.getIdByNickname(nickname);
        if (id==0)
            return new ResponseStyle().failure("查无此人");
        return new ResponseStyle().success(postService.getPostByAuthId(id,nickname));
    }
    @RequestMapping(value = "/post/section/{sectionId}/{page}")
    public ResponseStyle getPostBySection(@PathVariable int sectionId,@PathVariable(required = false) int page,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(StringUtil.loginStatus);
        String nickname = "";
        if(user!=null){
            nickname=user.getNickname();
        }
        PageParam pageParam =new PageParam();
        pageParam.setDefaultPageSize(10);
        pageParam.setDefaultPage(1);
        if (page==0){
            pageParam.setDefaultPage(1);
        }else{
            pageParam.setDefaultPage(page);
        }
        return new ResponseStyle().success(postService.getPostBySection(pageParam,sectionId,0,nickname));
    }
    @RequestMapping(value = "/post/incView",method = RequestMethod.POST)
    public ResponseStyle incView(@RequestBody Post post){
        postService.incView(post.getId());
        return new ResponseStyle().success();
    }

    @RequestMapping(value = "/post/pick",method = RequestMethod.DELETE)
    public ResponseStyle cancelPraise(HttpServletRequest request,@RequestBody Post post){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(StringUtil.loginStatus);
        postService.cancelPraise(user.getId(),post.getId());
        return new ResponseStyle().success();
    }
    @RequestMapping(value = "/post/collection",method = RequestMethod.DELETE)
    public ResponseStyle cancelCollection(HttpServletRequest request,@RequestBody Post post){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(StringUtil.loginStatus);
        postService.cancelCollection(user.getId(),post.getId());
        return new ResponseStyle().success();
    }

    static class Param{
        int postId;
        String reason;


        public Param() {
        }

        public int getPostId() {
            return postId;
        }

        public void setPostId(int postId) {
            this.postId = postId;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

    }


}
