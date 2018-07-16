package com.jvmup.nbbs.dao;

import com.jvmup.nbbs.po.Comment;
import com.jvmup.nbbs.po.Post;
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
    List<Comment> getComment(int id);
    int getLikeNum(int id);
    int getUnlikeNum(int id);
    Post getPost(int id);

    /**
     * 获取Post 可以根据post的参数获取精华帖，置顶帖
     * @param post
     * @param type 不同的排序方式，type为1表示按照发表时间，type为2表示按照最后回复，type为3表示按照系统热度
     * @return list
     */
    List<Post> getPost(@Param("post") Post post,@Param("type") int type);

    /**
     * 根据版块获取帖子
     * @param sectionId 版块的ID
     * @return list
     */
    List<Post> getPostBySection(int sectionId);

    /**
     * 获取首页推荐的热帖
     * @return list
     */
    List<Post> getHotPost();

    Post getPostById(int id);

    void deletePost(int id);

    void addPost(Post post);

    void toppingPost(int id);

    void changeType(Post post);

    void incView(int id);


}
