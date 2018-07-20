package com.jvmup.nbbs.service;

import com.jvmup.nbbs.dao.CommentDao;

import com.jvmup.nbbs.dao.UserDao;
import com.jvmup.nbbs.exception.DataInValidException;
import com.jvmup.nbbs.exception.NoPermissionToGetData;
import com.jvmup.nbbs.po.Comment;
import com.jvmup.nbbs.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-16 11:43
 **/
@Service
public class CommentService {
    private UserDao userDao;
    private CommentDao commentDao;


    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setCommentDao(CommentDao commentDao){
        this.commentDao=commentDao;
    }
//    /**
//     * @author lhm
//     * <p>
//     *     通过id获取评论相关的非用户信息
//     * </p>
//     */
//    public Comment getCommentInfoExceptUserById(int id){
//        return commentDao.getCommentInfoExceptUserById(id);
//    }
//    /**
//     * @author lhm
//     * <p>
//     *     通过评论id获取用户信息
//     * </p>
//     */
//    public User getUserInfoById(int id){
//        return commentDao.getUserInfoById(id);
//    }
    /**
     * @author lhm
     * <p>
     *     通过评论id删除评论
     * </p>
     */
    public void deleteCommentById(int id,int userId) throws DataInValidException {
        Integer a = userDao.getIdByCommentId(id);
        if (a==null)
            throw new DataInValidException();
        if (a==userId)
            commentDao.deleteCommentById(id);
        else if (userDao.isAdmin(userId)==1|| hasPermission(id,userId))
            commentDao.deleteCommentById(id);
        else
            throw new NoPermissionToGetData("没有权限删除");

    }
    /**
     * @author lhm
     * <p>
     *     通过post id获取该post下所有评论
     * </p>
     */
    public List<Comment> getAllCommentByPostId(int postId){
        return commentDao.getAllCommentByPostId(postId);
    }
    /**
     * @author lhm
     * <p>
     *  添加一条评论
     * </p>
     */
    public void addComment(Comment comment){
        commentDao.addComment(comment);
        commentDao.updateLastModify(comment.getPost().getId());
    }

    public List<Comment> getCommentById(int userId){
        return commentDao.getAllCommentByUserId(userId);
    }

    private boolean hasPermission(int id,int userId) throws DataInValidException {
        Map<String,Integer> map = commentDao.getCommentById(id);
        if (map.size()==0)
            throw new DataInValidException();
        if (userDao.isAs(userId,map.get("section_id"))>=1){
            return true;
        }else
            return userDao.isAp(userId, map.get("partition_id")) >= 1;
    }
}
