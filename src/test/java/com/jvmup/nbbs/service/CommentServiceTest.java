package com.jvmup.nbbs.service;

import com.jvmup.nbbs.exception.DataInValidException;
import com.jvmup.nbbs.po.Comment;
import com.jvmup.nbbs.po.Post;
import org.apache.ibatis.annotations.Param;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import com.jvmup.nbbs.exception.UserNotExistException;
import com.jvmup.nbbs.po.Sex;
import com.jvmup.nbbs.po.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;
@ContextConfiguration("classpath*:/applicationContext/applicationContext.xml")
public class CommentServiceTest extends AbstractTestNGSpringContextTests{
    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }


    @Test
    public void testDeleteCommentById() throws DataInValidException {
       commentService.deleteCommentById(1,4);
    }

    @Test
    public void testGetAllCommentByPostId() {
        logger.info(commentService.getCommentById(1));
    }

    @Test
    public void testAddComment() {
        Comment comment = new Comment();
        Comment comment1 = new Comment();
        comment1.setId(1);
        comment.setComment(comment1);
        comment.setContent("我是测试用的回复");
        Post post = new Post();
        post.setId(1);
        comment.setPost(post);
        User user = new User();
        user.setId(5);
        comment.setUser(user);
        commentService.addComment(comment);
    }

    @Test
    public void testGetCommentById() {
        List<Comment> comments = commentService.getAllCommentByPostId(1);
        logger.info(comments);
        logger.info(comments.size());
    }
}