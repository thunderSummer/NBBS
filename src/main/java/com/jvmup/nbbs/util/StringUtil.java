package com.jvmup.nbbs.util;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-14 17:50
 **/
public class StringUtil {
    public static String loginStatus = "loginStatus";
    public static boolean isBlank(String s){
        return s==null||s.equals("");
    }
    public static String login="您正在尝试登录jvmup,请确认是本人操作";
    public static String register="您正在尝试注册jvmup,请确认是本人操作";
    public static String forget="您正在尝试重置jvmup密码,请确认是否为本人操作";
    public static String mail="您正在尝试重置邮箱账号,请确认是否为本人操作";
    public static String vCode="vCodeAndTime";

}
