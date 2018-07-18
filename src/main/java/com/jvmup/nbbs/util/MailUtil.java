package com.jvmup.nbbs.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * ProjectName: NBBS
 * 发送邮箱实体类
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-16 20:09
 **/
public class MailUtil {
    private JavaMailSenderImpl mailSender;

    @Autowired
    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }
    private SimpleMailMessage mailMessage;
    @Autowired
    public void setMailMessage(SimpleMailMessage mailMessage) {
        this.mailMessage = mailMessage;
    }

    private Properties properties;
    @Resource
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    //发送验证码的方法,to是目标邮箱地址，text是发送的验证码（事先生成）
    public boolean sendMail (String to,String text,String code) {
        System.out.println("sendMail...util...");

        try{
            //设定mail server
            mailSender.setHost("smtp.163.com");

            // 设置收件人，寄件人 用数组发送多个邮件
            // String[] array = new String[]    {"sun111@163.com","sun222@sohu.com"};
            // mailMessage.setTo(array);

            mailMessage.setTo(to);
            mailMessage.setFrom( "jvmupmail@163.com" );
            mailMessage.setSubject( "jvmup官网验证码" );
            mailMessage.setText("内容:" + text+"。验证码是"+code+"(该验证码5分钟有效，请妥善保管)");

            mailSender.setUsername("jvmupmail@163.com");
            mailSender.setPassword("jvmup666");

            properties.put("mail.smtp.auth","true");
            properties.put("mail.smtp.timeout","25000");
            mailSender.setJavaMailProperties(properties);

            //发送邮件
            mailSender.send(mailMessage);

            System.out.println("发送邮件成功");

            return true;
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送邮件失败");
            return false;
        }
    }

}
