package com.jvmup.nbbs.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;


@ContextConfiguration("classpath*:/applicationContext/applicationContext.xml")
public class MailUtilTest extends AbstractTestNGSpringContextTests {
    private MailUtil mailUtil;

    @Autowired
    public void setMailUtil(MailUtil mailUtil) {
        this.mailUtil = mailUtil;
    }

    @Test
    public void testSendMail(){
        mailUtil.sendMail("thunder66666@163.com","您正在尝试注册jvmup","45672");
    }

}