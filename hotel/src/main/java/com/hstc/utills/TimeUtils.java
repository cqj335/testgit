package com.hstc.utills;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeUtils {
    public static Date getTime(String string) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date time=sdf.parse(string);
        return time;
    }
}
