package com.jvmup.nbbs.controller;
import com.jvmup.nbbs.po.ResponseStyle;
import com.jvmup.nbbs.po.Comment;
import com.jvmup.nbbs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/allComment//{postId}",method = RequestMethod.GET)
    public ResponseStyle getAllCommentOfThisPost (@PathVariable int postId){
        return new ResponseStyle().success(commentService.getAllCommentByPostId(postId));
    }
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.DELETE)
    public ResponseStyle deleteCommentById (@PathVariable int id){
        commentService.deleteCommentById(id);
        return new ResponseStyle().success();
    }
    @RequestMapping(value = "/comment/",method = RequestMethod.POST)
    public ResponseStyle addComment (@PathVariable Comment comment){
        commentService.addComment(comment);
        return new ResponseStyle().success(comment.getId());
    }
//    @RequestMapping(value = "/comment/userInfo/{id}",method = RequestMethod.GET)
//    public ResponseStyle getUserIfoById (@PathVariable int id){
//        return new ResponseStyle().success(commentService.getUserInfoById(id));
//    }
//    @RequestMapping("/comment/otherInfo/{id}")
//    public ResponseStyle getCommentInfoExceptUserById (@PathVariable int id){
//        return new ResponseStyle().success(commentService.getCommentInfoExceptUserById(id));
//    }

}
