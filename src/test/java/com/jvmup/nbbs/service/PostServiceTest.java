package com.jvmup.nbbs.service;

import com.jvmup.nbbs.po.Post;
import com.jvmup.nbbs.po.Section;
import com.jvmup.nbbs.po.User;
import com.jvmup.nbbs.util.PageParam;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
@ContextConfiguration("classpath*:/applicationContext/applicationContext.xml")
public class PostServiceTest extends AbstractTestNGSpringContextTests {

    private PostService postService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }


    @Test
    public void testGetPostBySection() {
//        PageParam pageParam = new PageParam();
//        pageParam.setDefaultPage(1);
//        pageParam.setDefaultPageSize(5);
//        postService.getPostBySection(pageParam,1,1);
    }

    @Test
    public void testGetHotPost() {
        postService.getHotPost("我是第一个用户");
    }

    @Test
    public void testGetPostByPostId() {
        postService.getPostByPostId(1);
    }

    @Test
    public void testDeletePost() throws Exception {
        postService.deletePost(25,6);
    }

    @Test
    public void testAddPost() {
        Post post = new Post();
        post.setTitle("这是一个森啊扥东方");
        post.setContent("sdfadfs");
        post.setTopping(1);
        User user = new User();
        user.setId(6);
        post.setUser(user);
        Section section = new Section();
        section.setId(3);
        post.setSection(section);
        postService.addPost(post);
    }

    @Test
    public void testToppingPost() {
        postService.getToppingPost("我是第一个用户");
    }

    @Test
    public void testChangeType() {
    }

    @Test
    public void testPraise() {
        Post post = new Post();
        post.setId(1);
        postService.praise(post,2);
    }

    @Test
    public void testGetPraiseCnt() {
        postService.getPraiseCnt(1);
    }

//    @Test
//    public void testGetPostByAuthId() {
//        postService.getPostByAuthId(2);
//    }

    @Test
    public void testGetCollectionCnt() {
        postService.getCollectionCnt(1);
    }

    @Test
    public void testCollect() {
        postService.collect(1,1);
    }

    @Test
    public void testReport() {
        postService.Report(1,2,"你是傻逼");
    }


    @Test
    public void testIncView() {
        postService.incView(1);
    }

    @Test
    public void testGetToppingPost() {
    }

    @Test
    public void testSearchPost() {
        postService.searchPost("用户",1,1);
    }
}