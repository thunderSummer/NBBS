package com.jvmup.nbbs.service;

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
public class UserServiceTest extends AbstractTestNGSpringContextTests {
    private Logger logger = LogManager.getLogger();
    private UserService userService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void testGetIdByNickname() {
        int id = userService.getIdByNickname("我是第一个用户");
        assertEquals(id,1);
    }

    @Test
    public void testGetIdByEmail() {
        assertEquals(userService.getIdByEmail("test1@qq.com"),1);
    }

    @Test
    public void testGetUserInfo() {
        logger.info(userService.getUserInfo(1));
    }

    @Test
    public void testRegister() {
        User user = new User();
        user.setPassword("123");
        user.setEmail("sdf");
        user.setNickname("dsf");
        userService.register(user);
    }

    @Test
    public void testUserLogin() throws UserNotExistException {
        User user = new User();
        user.setPassword("123");
        user.setEmail("sdf");
        user.setNickname("dsf");
        assertTrue(userService.userLogin(user));
    }

    @Test
    public void testGetNicknameByEmail() {
        assertEquals(userService.getNicknameByEmail("test1@qq.com"),"我是第一个用户");
    }

    @Test
    public void testUpdateUserInfo() {
        User user = new User();
        user.setId(10);
        user.setSex(Sex.FEMALE);
        user.setAvatar("www.baidu.com");
        userService.updateUserInfo(user);
    }

    @Test
    public void testUpdateMail() {
        User user = new User();
        user.setId(10);
        user.setNickname("dsf");
        user.setEmail("www.baidu.com");
        userService.updateMail(user);
    }

    @Test
    public void testUpdatePassword() {
        User user = new User();
        user.setId(10);
        user.setNickname("dsf");
        user.setPassword("sdf");
        userService.updatePassword(user);
    }

    @Test
    public void testUpdateEx() {
        User user = new User();

        user.setNickname("dsf");
    }

    @Test
    public void testUpdateStatus() {
        User user=new User();
        user.setNickname("dsf");

        userService.updateEx(user,3);
    }

    @Test
    public void testGetFans() {
        List<User> users = userService.getFans("我是第一个用户");
        for (User user : users){
            logger.info(user);
        }
    }

    @Test
    public void testGetFollows() {
        List<User> users = userService.getFollows("我是第一个用户");
        for (User user : users){
            logger.info(user);
        }
    }

    @Test
    public void testGetMaskWord() {
        User user =new User();
        user.setNickname("我是第一个用户");
        for (String s: userService.getMaskWord("我是第一个用户"));
    }

    @Test
    public void testGetBlack() {
        List<User> users = userService.getBlack("我是第一个用户");
        for (User user : users){
            logger.info(user);
        }
    }

    @Test
    public void testAddFans() {
        userService.addFans("我是第三个用户","我是第一个用户");
    }

    @Test
    public void testAddBlack() {
        userService.addBlack("我是第六个用户","我是第一个用户");
    }

    @Test
    public void testAddWord() {
        userService.addWord("我是第一个用户","nmsl");
    }

    @Test
    public void testRemoveFans() {
        userService.removeFans("我是第三个用户","我是第一个用户");
    }

    @Test
    public void testRemoveBlack() {
        userService.removeBlack("我是第六个用户","我是第一个用户");
    }

    @Test
    public void testRemoveWord() {
        userService.removeWord("我是第一个用户","nmsl");
    }

    @Test
    public void testGetFollow() {
        userService.getFollows("我是第-个用户");
    }

    @Test
    public void testAddFollow(){
        userService.addFollow("我是第一个用户","我是第四个用户");

    }

    @Test
    public void testRemoveFollow(){
        userService.addFollow("我是第一个用户","我是第四个用户");

    }
}