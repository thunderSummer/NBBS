package com.jvmup.nbbs.service;

import com.jvmup.nbbs.dao.PostDao;
import com.jvmup.nbbs.dao.UserDao;
import com.jvmup.nbbs.po.Post;
import com.jvmup.nbbs.util.PageParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jvmup.nbbs.po.User;
import java.util.List;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-16 11:43
 **/
@Service
public class PostService {
    private PostDao postDao;
    public User user;

    @Autowired
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }


    /**
     * gzp 717 1550
     * 根据版块获取帖子
     * @param sectionId
     * @return
     */
    public List<Post> getPostBySection(PageParam pageParam,  int sectionId, int type){

        return postDao.getPost(pageParam,sectionId,type);
    }
    /**
     * gzp 717 1553
     * 获取首页推荐的热帖
     * @return list
     */
    public List<Post> getHotPost(){
        return postDao.getHotPost();
    }
    /**
     * 通过id获得post
     * @param id
     * @return
     */
    public Post getPostByPostId(int id){
        return postDao.getPostByPostId(id);
    }
    /**
     * 删除post
     * @param id
     */
    public void deletePost(int id){
        postDao.deletePost(id);
    }
    /**
     * 添加post
     * @param post
     */
   public void addPost(Post post){
        postDao.addPost(post);
    }
    /**
     * 置顶post
     * @param id
     */
   public void toppingPost(int id){
        postDao.toppingPost(id);
    }
    /**
     * gzp
     * 改变文章类型
     * @param postId
     *
     */
    public void changeType(int postId,int sectionId){
        postDao.changeType(postId,sectionId);
    }
    /**
     *
     * 点赞
     * @param postId
     * @param userId
     */
    public void praise(int postId,int userId){
        postDao.praise(postId,userId);
    }
    /**
     * @param id postid
     * @return 点赞数
     */
    public int getPraiseCnt(int id){
        return postDao.getPraiseCnt(id);
    }
    /**
     * 根据作者ID获得post
     * @param id 作者ID
     */
    public List<Post> getPostByAuthId(int id){
        return postDao.getPostByAuthId(id);
    }
    /**
     * 根据文章ID获取收藏次数
     * @param postId
     */
    public int getCollectionCnt(int postId){
        return postDao.getCollectionCnt(postId);
    }
    /**
     * @param postId
     * @param userId
     */
    public void collect(int postId,int userId){
        postDao.collect(postId,userId);
    }
    /**
     *
     * @param userId 用户id
     * @param postId postid
     * @param reason 原因
     */
    public void Report(int userId,int postId,String reason) {
        postDao.Report(userId, postId, reason);
    }
    /**
     * gzp 718 1303
     *
     */
    public void getPostDetail(int postId){
        postDao.getPraiseCnt(postId);
        postDao.getCollectionCnt(postId);
    }

    /**
     * 阅读次数
     * @param id
     */

    public void incView(int id){
        postDao.incView(id);
    }
}





















