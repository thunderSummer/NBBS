package com.jvmup.nbbs.controller;

import com.jvmup.nbbs.po.Partition;
import com.jvmup.nbbs.po.ResponseStyle;
import com.jvmup.nbbs.util.MailUtil;
import com.jvmup.nbbs.util.StringUtil;
import com.jvmup.nbbs.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Email;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-16 21:28
 **/
@RestController
public class CodeController {
    private MailUtil mailUtil;

    @Autowired
    public void setMailUtil(MailUtil mailUtil) {
        this.mailUtil = mailUtil;
    }

    @RequestMapping(value = "/user/verification",method = RequestMethod.POST)
    public ResponseStyle handleVCode(HttpServletRequest request, @RequestBody Param param){
       String vCode = String.valueOf((int)((Math.random()*9+1)*100000));
       switch (param.type){
           case 1:
               //用于用户注册密码
               mailUtil.sendMail(param.email,StringUtil.register,vCode);
               break;
           case 2:
               //用于风险用户登录
               mailUtil.sendMail(param.email,StringUtil.login,vCode);
               break;
           case 3:
               //用于重置密码，无论是忘记密码还是直接重置密码
               mailUtil.sendMail(param.email,StringUtil.forget,vCode);
               break;
           case 4:
               //用于重置邮箱密码
               mailUtil.sendMail(param.email,StringUtil.mail,vCode);
               break;
               default:
                   return new ResponseStyle().failure("格式错误");
       }
        HttpSession session = request.getSession();
       session.setAttribute(StringUtil.vCode,vCode+"#"+param.email);
       return new ResponseStyle().success("验证码发送成功");

    }

    class Param{
        @Email
        private String email;
        private int type;

        public String getEmail() {
            return email;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getType() {
            return type;
        }
    }
}
