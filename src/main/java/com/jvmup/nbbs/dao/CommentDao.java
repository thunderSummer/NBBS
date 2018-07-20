package com.jvmup.nbbs.dao;

import com.jvmup.nbbs.po.User;
import com.jvmup.nbbs.po.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-16 11:30
 **/
public interface CommentDao {
//    /**
//     * @author lhm
//     * <p>
//     *     通过id获取评论相关的非用户信息
//     * </p>
//     */
//    Comment getCommentInfoExceptUserById(int id);
//    /**
//     * @author lhm
//     * <p>
//     *     通过评论id获取用户信息
//     * </p>
//     */
//    User getUserInfoById(int id);
    /**
     * @author lhm
     * <p>
     *     通过评论id删除评论
     * </p>
     */
    void deleteCommentById(int id);
    /**
     * @author lhm
     * <p>
     *     通过post id获取该post下所有评论
     * </p>
     */
    List<Comment> getAllCommentByPostId(int postId);
    /**
     * @author lhm
     * <p>
     *  添加一条评论
     * </p>
     */
    void addComment(Comment comment);

    /**
     * 更新某个帖子的最后回复时间,和addComment 一起使用
     * @param postId
     */
    void updateLastModify(int postId);


    /**
     * 获取一个用户的所有的评论，
     * @param userId
     */
    List<Comment> getAllCommentByUserId(int userId);

    Map <String,Integer>getCommentById(int id);
}
