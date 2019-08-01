package com.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateTools {

    private static Logger logger = LoggerFactory.getLogger(DateTools.class);

    public static final String DEFAULTPATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String PATTERN_YYYY_MM = "yyyy-MM";
    public static final String PATTERN_yyyyMMddhhmmss = "yyyyMMddHHmmss";
    public static final String PATTERN_yyyyMMdd = "yyyyMMdd";
    public static final String PATTERN_yyyyMMddHHmm = "yyyy-MM-dd HH:mm";
    public static final String PATTERN_yyyyMMddHH = "yyyy-MM-dd HH";
    public static final String PATTERN_YYYY_MM_DD2 = "yyyy年MM月dd日";
    public static final String PATTERN_MM_DD = "MM月dd日";
    public static final String PATTERN_hh_ss = "HH:mm";
    public static final String DEFAULTPATTERN_EN = "EEE MMM dd HH:mm:ss Z yyyy";

    public static String DateToStr(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            String str = format.format(date);
            return str;
        } else {
            return null;
        }
    }

}