package com.jvmup.nbbs.dao;

import com.jvmup.nbbs.po.Comment;
import com.jvmup.nbbs.po.Post;
import com.jvmup.nbbs.util.PageParam;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-15 21:50
 **/
public interface PostDao {
    /**
     * 根据版块获取帖子
     * 不同的排序方式，type为1表示按照发表时间，type为2表示按照最后回复，type为3表示按照系统热度
     * @return list
     */
    List<Post> getPost(PageParam pageParam, @Param("sectionId") int sectionId, @Param("type") int type);
    /**
     * 获取首页推荐的热帖
     * @return list
     */

    List<Post> getHotPost();
    /**
     * 通过id获得post
     * @param id
     * @return
     */
    Post getPostByPostId(int id);

     /**
     * 删除post
     * @param id
     */
    void deletePost(int id);

    /**
     * 添加post
     * @param post
     */
    void addPost(Post post);

    /**
     * 置顶post
     * @param id
     */
    void toppingPost(int id);

    /**
     * gzp 717 1201
     *改变文章的版块
     * @param postId
     * @param sectionId
     */
    void changeType(@Param("postId") int postId, @Param("sectionId") int sectionId);
    /**
     * gzp 717 1234
     * 点赞
     * @param postId
     * @param userId
     */
    void praise(@Param("postId") int postId, @Param("userId") int userId);

    /**
     * gzp 717 1452
     * @param id postId
     * @return 点赞数
     */
    int getPraiseCnt(int id);
    /**
     * gzp 717 1446
     * @param postId
     * @param userId
     */
    void collect(@Param("postId") int postId, @Param("userId") int userId);
    /**
     * gzp 727 1421
     * 根据文章ID获取收藏次数
     * @param postId
     */
    int getCollectionCnt(int postId);
    /**
     * gzp 717 1348
     * 根据作者ID获得post
     * @param id 作者ID
     */
    List<Post> getPostByAuthId(int id);
    /**
     *
     * @param userId 用户id
     * @param postId postid
     * @param reason 原因
     */
    void Report(@Param("userId") int userId, @Param("postId") int postId, @Param("reason") String reason);

    /**
     * 获得简介
     * @param id 帖子ID
     */
    void getBrief(int id);

    /**
     * 阅读次数
     * @param id
     */

    void incView(int id);
}
