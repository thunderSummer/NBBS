package com.jvmup.nbbs.util;

import java.util.Calendar;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-16 20:22
 **/
public class TimeUtil {
    public static String getTime() {

        Calendar calendar = Calendar.getInstance();
        Long date = calendar.getTime().getTime();            //获取毫秒时间

        return date.toString();
    }

    public static boolean cmpTime(String time) {
        long tempTime = Long.parseLong(time);

        //获取现在的时间
        Calendar calendar = Calendar.getInstance();
        Long date = calendar.getTime().getTime();

        if (date - tempTime > 600000) {   //10分钟
            return false;
        } else {
            return true;
        }
    }
}
