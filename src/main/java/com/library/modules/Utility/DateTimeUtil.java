package com.library.modules.Utility;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil
{
    private static SimpleDateFormat dateTimeFormat=new SimpleDateFormat("dd-MM-yyyy");
    public static String getFormattedDate(Timestamp timestamp)
    {
        if(timestamp==null)
        {
            return "";
        }
        Date date=new Date(timestamp.getTime());
        return dateTimeFormat.format(date);
    }
    public static String getFormattedDateAfter(int noOfDays)
    {
        Calendar calendar =Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        calendar.add(Calendar.DAY_OF_WEEK,noOfDays);
        Date date=new Date(calendar.getTimeInMillis());
        return dateTimeFormat.format(date);
    }
    public static Timestamp getTimestampAfter(int noOfDays)
    {
        Calendar calendar =Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        calendar.add(Calendar.DAY_OF_WEEK,noOfDays);
        return new Timestamp(calendar.getTime().getTime());
    }
}
