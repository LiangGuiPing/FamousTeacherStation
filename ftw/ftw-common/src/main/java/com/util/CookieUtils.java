package com.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * About Cookie Info
 * @author LGP
 * @data 2018/09/14
 */
public class CookieUtils 
{
    public static String getCookie(HttpServletRequest request, String cookieName)
    {
        Cookie[] cookies =  request.getCookies();
        if(cookies != null)
        {
            for(Cookie cookie : cookies)
            {
                if(cookie.getName().equals(cookieName))
                {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void writeCookie(HttpServletResponse response, String cookieName, String value)
    {
        Cookie cookie = new Cookie(cookieName,value);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
    }
    
    public static void delCookie(HttpServletRequest request, HttpServletResponse response, String name)
    {
        Cookie[] cookies = request.getCookies();
        if(null != cookies) 
        {
            for(Cookie cookie : cookies)
            {
                if(cookie.getName().equals(name))
                {
                    cookie.setValue(null);  
                    cookie.setMaxAge(0); 
                    cookie.setPath("/");  
                    response.addCookie(cookie);  
                    break;  
                }  
            }
        }
    }

}