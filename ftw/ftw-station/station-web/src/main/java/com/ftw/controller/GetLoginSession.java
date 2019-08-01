package com.ftw.controller;

import com.ftw.entity.adm.AdmUsers;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class GetLoginSession {
    public static AdmUsers GetUser(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        AdmUsers admuser = (AdmUsers) request.getSession().getAttribute("admuser");
        return admuser;
    }
}
