package com.jvmup.nbbs.service;

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
        PageParam pageParam = new PageParam();
        pageParam.setDefaultPageSize(3);
        postService.getPostBySection(pageParam,1,1);
    }

    @Test
    public void testGetHotPost() {
    }

    @Test
    public void testGetPostByPostId() {
    }

    @Test
    public void testDeletePost() {
    }

    @Test
    public void testAddPost() {
    }

    @Test
    public void testToppingPost() {
    }

    @Test
    public void testChangeType() {
    }

    @Test
    public void testPraise() {
    }

    @Test
    public void testGetPraiseCnt() {
    }

    @Test
    public void testGetPostByAuthId() {
    }

    @Test
    public void testGetCollectionCnt() {
    }

    @Test
    public void testCollect() {
    }

    @Test
    public void testReport() {
    }

    @Test
    public void testGetPostDetail() {
    }

    @Test
    public void testIncView() {
    }
}