package com.jvmup.nbbs.controller;
import com.jvmup.nbbs.po.ResponseStyle;
import com.jvmup.nbbs.po.Comment;
import com.jvmup.nbbs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProjectName: NBBS
 *@author lhm
 *
 *  **/

@RestController
public class CommentController {
    private CommentService commentService;
    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping("/comment/all/{post_id}")
    public ResponseStyle getAllCommentOfThisPost (@PathVariable int post_id){
        return new ResponseStyle().success(commentService.getAllCommentByPostId(post_id));
    }
    @RequestMapping("/comment/delete/{id}")
    public ResponseStyle deleteCommentById (@PathVariable int id){
        commentService.deleteCommentById(id);
        return new ResponseStyle().success();
    }
    @RequestMapping("/comment/addNew/{id}")
    public ResponseStyle addComment (@PathVariable Comment comment){
        commentService.addComment(comment);
        return new ResponseStyle().success();
    }
    @RequestMapping("/comment/userInfo/{id}")
    public ResponseStyle getUserIfoById (@PathVariable int id){
        return new ResponseStyle().success(commentService.getUserIfoById(id));
    }
    @RequestMapping("/comment/otherInfo/{id}")
    public ResponseStyle getCommentInfoExceptUserById (@PathVariable int id){
        return new ResponseStyle().success(commentService.getCommentInfoExceptUserById(id));
    }

}
