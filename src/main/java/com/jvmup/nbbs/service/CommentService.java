package com.jvmup.nbbs.service;

import com.jvmup.nbbs.dao.CommentDao;

import com.jvmup.nbbs.po.Comment;
import com.jvmup.nbbs.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-16 11:43
 **/
@Service
public class CommentService {
    private CommentDao commentDao;
    @Autowired
    public void setCommentDao(CommentDao commentDao){
        this.commentDao=commentDao;
    }
    /**
     * @author lhm
     * <p>
     *     通过id获取评论相关的非用户信息
     * </p>
     */
    public Comment getCommentInfoExceptUserById(int id){
        return commentDao.getCommentInfoExceptUserById(id);
    }
    /**
     * @author lhm
     * <p>
     *     通过评论id获取用户信息
     * </p>
     */
    public User getUserIfoById(int id){
        return commentDao.getUserIfoById(id);
    }
    /**
     * @author lhm
     * <p>
     *     通过评论id删除评论
     * </p>
     */
    public void deleteCommentById(int id){
        commentDao.deleteCommentById(id);
    }
    /**
     * @author lhm
     * <p>
     *     通过post id获取该post下所有评论
     * </p>
     */
    public List<Comment> getAllCommentByPostId(int post_id){
        return commentDao.getAllCommentByPostId(post_id);
    }
    /**
     * @author lhm
     * <p>
     *  添加一条评论
     * </p>
     */
    public void addComment(Comment comment){
        commentDao.addComment(comment);
    }

}
