package com.jvmup.nbbs.controller;
import com.jvmup.nbbs.po.Post;
import com.jvmup.nbbs.po.ResponseStyle;
import com.jvmup.nbbs.po.User;
import com.jvmup.nbbs.service.PostService;

import com.jvmup.nbbs.service.UserService;
import com.jvmup.nbbs.util.StringUtil;
import org.apache.ibatis.annotations.ResultMap;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public void setPostService(PostService postService){
        this.postService=postService;
    }
    private PostService postService;
    /**
     * gzp 717 1957
     * addpost
     */
    @RequestMapping(value ="posts/add", method = RequestMethod.POST)
    public ResponseStyle addPost(@RequestBody Post post, HttpServletRequest request){
        HttpSession session= request.getSession();
        User u = (User) session.getAttribute(StringUtil.loginStatus);
        post.getUser().setId(u.getId());
        postService.addPost(post);
        return new ResponseStyle().success();
    }
    /**
     * gzp 718 919
     * 删除帖子
     */
    @RequestMapping(value = "posts/delete",method = RequestMethod.DELETE)
    public ResponseStyle deletePost(@RequestBody Post post,HttpServletRequest request){
        postService.deletePost(post.getId());
        return new ResponseStyle().success();
    }
    /**
     * gzp 718 927
     * 点赞
     */
    @RequestMapping(value = "posts/praise",method = RequestMethod.POST)
    public ResponseStyle praisePost(@RequestBody Post post, HttpServletRequest request){
        HttpSession session=request.getSession();
        User u=(User) session.getAttribute(StringUtil.loginStatus);
        postService.praise(post.getId(),u.getId());
        return new ResponseStyle().success();
    }
    /**
     * gzp 718 939
     * 收藏
     */
    @RequestMapping(value = "posts/collect",method = RequestMethod.POST)
    public ResponseStyle collectPost(@RequestBody Post post,HttpServletRequest request){
        HttpSession session=request.getSession();
        User u= (User) session.getAttribute(StringUtil.loginStatus);
        postService.collect(post.getId(),u.getId());
        return new ResponseStyle().success();
    }
    /**
     * gzp 718 947
     * &#x5e16;&#x5b50;&#x8be6;&#x60c5;
     */
    @RequestMapping(value = "posts/detail",method = RequestMethod.GET)
    public ResponseStyle getPostDetail(@RequestBody Post post,HttpServletRequest request){
        postService.getPostByPostId(post.getId());
        return new ResponseStyle().success();
    }
    /**
     * gzp 718 1003
     * 获得分区帖子
     */
    @RequestMapping(value = "posts/{section_id}",method = RequestMethod.GET)
    public ResponseStyle getPostsBySection(int section_id){
//        postService.getPostBySection(section_id);
        return new ResponseStyle().success();
    }
    /**
     * gzp 718 1232
     * 获得首页帖子
     */
    @RequestMapping(value = "post" ,method = RequestMethod.GET)
    public ResponseStyle getHomePosts(){
        postService.getHotPost();
        return new ResponseStyle().success();
    }
    /**
     * gzp 718 1317
     * 获得用户文章
     */
    @RequestMapping(value = "{user_id}/posts",method = RequestMethod.GET)
    public ResponseStyle getAuthPosts(HttpServletRequest request){
        HttpSession session=request.getSession();
        User u= (User) session.getAttribute(StringUtil.loginStatus);
        postService.getPostByAuthId(u.getId());
        return new ResponseStyle().success();
    }

}
