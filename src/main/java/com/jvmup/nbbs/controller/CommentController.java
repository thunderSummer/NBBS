package com.jvmup.nbbs.controller;
import com.jvmup.nbbs.exception.DataInValidException;
import com.jvmup.nbbs.po.ResponseStyle;
import com.jvmup.nbbs.po.Comment;
import com.jvmup.nbbs.po.User;
import com.jvmup.nbbs.service.CommentService;
import com.jvmup.nbbs.service.UserService;
import com.jvmup.nbbs.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ProjectName: NBBS
 *@author lhm
 *
 *  **/

@RestController
public class CommentController {
    private CommentService commentService;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * @author lhm
     * <p>
     *     通过评论id删除评论
     * </p>
     */
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.DELETE)
    public void deleteCommentById(@PathVariable  int id, HttpServletRequest request) throws DataInValidException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(StringUtil.loginStatus);
       commentService.deleteCommentById(id,user.getId());
    }
    /**
     * @author lhm
     * <p>
     *     通过post id获取该post下所有评论
     * </p>
     */
    @RequestMapping(value = "/comment/post/{postId}",method = RequestMethod.GET)
    public ResponseStyle getAllCommentByPostId(@PathVariable int postId){
        return new ResponseStyle().success(commentService.getAllCommentByPostId(postId));
    }
    /**
     * @author lhm
     * <p>
     *  添加一条评论
     * </p>
     */
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public ResponseStyle addComment(@PathVariable Comment comment,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(StringUtil.loginStatus);
        comment.setUser(user);
        comment.setContent(replace(user.getNickname(),comment.getContent()));
        commentService.addComment(comment);
        return new ResponseStyle().success();
    }

    @RequestMapping(value = "/comment/user/{nickname}",method = RequestMethod.GET)
    public ResponseStyle getCommentByUserId(@PathVariable String nickname){
        int id = userService.getIdByNickname(nickname);
        if (id==0)
            return new ResponseStyle().success().failure("查无此人");
        return new ResponseStyle().success(commentService.getCommentById(id));
    }
    private String replace(String nickname,String content){
        List<String> strings = userService.getMaskWord(nickname);
        String res = content;
        for (String s:strings){
            res = res.replace(s,"***");
        }
        return res;
    }

}

