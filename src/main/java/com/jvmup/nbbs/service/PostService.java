package com.jvmup.nbbs.service;

import com.jvmup.nbbs.dao.PostDao;
import com.jvmup.nbbs.dao.UserDao;
import com.jvmup.nbbs.exception.DataInValidException;
import com.jvmup.nbbs.exception.NoPermissionToGetData;
import com.jvmup.nbbs.po.Post;
import com.jvmup.nbbs.util.PageParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jvmup.nbbs.po.User;

import java.util.ArrayList;
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
    private UserDao userDao;
    public User user;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * gzp 717 1550
     * 根据版块获取帖子
     * @param sectionId
     * @return
     */
    public List<PostWrapper> getPostBySection(PageParam pageParam,  int sectionId, int type,String nickname){

        return changePostList(nickname,postDao.getPost(pageParam,sectionId,type));
    }
    /**
     * gzp 717 1553
     * 获取首页推荐的热帖
     * @return list
     */
    public List<PostWrapper> getHotPost(String nickname){
        return changePostList(nickname,postDao.getHotPost());
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
    public void deletePost(int id,int userId) throws Exception {
        Integer a = userDao.getIdByPostId(id);
        if (a==null)
            throw new DataInValidException();
        if (userDao.getIdByPostId(id)==userId)
            postDao.deletePost(id);
        else if (userDao.isAdmin(userId)==1||hasPermission(id,userId))
            postDao.deletePost(id);
        else
            throw new NoPermissionToGetData("没有权限删除");
    }
    /**
     * 添加post
     * @param post
     */
   public void addPost(Post post){

        postDao.addPost(post);
        userDao.updateEx(post.getUser().getId(),10);
    }
    /**
     * 置顶post
     * @param id
     */
   public void toppingPost(int id,int userId,int toppingType){
       if (toppingType==2){
           if (userDao.isAdmin(userId)==1){
               postDao.toppingPost(id,toppingType);
           }else{
               throw new NoPermissionToGetData("没有权限去置顶该帖子");
           }
       }else{
           if (hasPermission(id,userId)){
               postDao.toppingPost(id,toppingType);
           }else{
               throw  new NoPermissionToGetData("没有权限去置顶该帖子");
           }
       }


    }
    /**
     * gzp
     * 改变文章类型
     * @param postId
     *
     */
    public void changeType(int userId, int postId,int sectionId){
        if (userDao.isAdmin(userId)==1||hasPermission(postId,userId))
        postDao.changeType(postId,sectionId);
        else
            throw new NoPermissionToGetData("没有权限去改变文章类型");
    }
    /**
     *
     * 点赞
     * @param userId
     */
    public void praise(Post post,int userId){
        postDao.praise(post.getId(),userId);
        userDao.updateEx(userService.getIdByPostId(post.getId()),2);
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
    public List<PostWrapper> getPostByAuthId(int id,String nickname){
        return changePostList(nickname,postDao.getPostByAuthId(id));
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
     * 阅读次数
     * @param id
     */

    public void incView(int id){
        postDao.incView(id);
    }

    public List<PostWrapper> getToppingPost(String nickname){
        return changePostList(nickname,postDao.getToppingPost());
    }
    private boolean hasPermission(int postId,int userId){
        Post post = postDao.getPostByPostId(postId);
        if (userDao.isAs(userId,post.getSection().getId())>=1){
            return true;
        }else
            return userDao.isAp(userId, post.getSection().getPartition().getId()) >= 1;
    }

    static class PostWrapper{
        Post post;
        boolean like =false;
        boolean collection=false;

        public boolean isCollection() {
            return collection;
        }

        public void setCollection(boolean collection) {
            this.collection = collection;
        }

        public Post getPost() {
            return post;
        }

        public void setPost(Post post) {
            this.post = post;
        }

        public void setLike(boolean like) {
            this.like = like;
        }


        public boolean isLike() {
            return like;
        }

        public PostWrapper() {
        }
    }
    private PostWrapper changePost(String nickname,Post post){
        PostWrapper postWrapper = new PostWrapper();
        postWrapper.setPost(post);
        if (post.getLikeList().size()!=0)
        for (User user:post.getLikeList()){
            if (user.getNickname().equals(nickname)){
                postWrapper.setLike(true);
                break;
            }
        }
        if (post.getCollectionList().size()!=0)
        for(User user:post.getCollectionList()){
            if (user.getNickname().equals(nickname)){
                postWrapper.setCollection(true);
                break;
            }
        }
        return postWrapper;
    }

    private List<PostWrapper> changePostList(String nickname,List<Post> posts){
        List<PostWrapper> wrappers = new ArrayList<>();
        for (Post post:posts){
            wrappers.add(changePost(nickname,post));
        }
        return wrappers;
    }

    public void cancelPraise(int userId,int postId){
        postDao.cancelPraise(postId,userId);
    }
    public void cancelCollection(int userId,int postId){
        postDao.cancelCollection(postId,userId);
    }
}





















