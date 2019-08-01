package com.ftw.controller.adm;

import com.ftw.entity.adm.AdmUsers;
import com.ftw.service.AdmUserService;
import com.util.CookieUtils;
import com.util.ResultModel;
import com.util.token.EncryptBySHA256;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

@Controller
@RequestMapping("/")
public class LoginController 
{
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Resource
	private AdmUserService admuserservice;

	@GetMapping(value = "/")
	public String index(Model model, HttpSession session) {
		AdmUsers admuser = (AdmUsers) session.getAttribute("admuser");
		model.addAttribute("admuser", admuser);
		return "login.html";
	}

	@GetMapping(value = "/login")
	public String login(Model model, HttpSession session) {
		AdmUsers admuser = (AdmUsers) session.getAttribute("admuser");
		model.addAttribute("admuser", admuser);
		return "login.html";
	}

	@GetMapping("/header")
	public String header() {
		return "admin/header.html";
	}
	
    @PostMapping("/do")
	@ResponseBody
    public Object index(String loginName, String password, Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
    	if(StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password))
    	{
    		return ResultModel.error(10001,"用户名或密码不能为空");
    	}
    	loginName = loginName.trim();
    	password = password.trim();

    	String newPassword = EncryptBySHA256.getEncryptString(password);
    	UsernamePasswordToken token = new UsernamePasswordToken(loginName, newPassword);
		Subject subject = SecurityUtils.getSubject();
		try 
		{
			AdmUsers admuser = admuserservice.findUserByLoginName(loginName);
			if(null != admuser)
			{
				if(0 == admuser.getStatus())
				{
					return ResultModel.error(10003, "该用户已被禁用");
				}
				else
				{
					if(new Date().after(admuser.getExpiretime()))
					{
						return ResultModel.error(10004, "该用户已过期");
					}
				}
			}

			subject.login(token);
			session.setAttribute("admuser", admuser);
			CookieUtils.writeCookie(response, "realName", URLEncoder.encode(admuser.getRealname(), "utf-8"));
			return ResultModel.success("登录成功");
		}
		catch (Exception e)
		{
			logger.error("用户登录出错:{}", e);
			this.delSessionAndCookies(request, response);
			return ResultModel.error(10002,"用户名或密码错误");
		}
    }
    
    @RequestMapping("/logOut")
    public void logOut(HttpSession session, HttpServletRequest request, HttpServletResponse response)
    {
    	try
    	{
        	Subject subject = SecurityUtils.getSubject();
        	subject.logout();
        	this.delSessionAndCookies(request, response);
        	response.sendRedirect("/login");
    	}
    	catch(Exception e) 
    	{
    		try 
    		{
				response.sendRedirect("/login");
			} 
    		catch (IOException h) 
    		{
    			logger.error("重定向出错:{}", h);
			}
    		logger.error("用户退出登录出错:{}", e);
    	}
    }
    
    @RequestMapping("/home")
    public String toAdmHome(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response)
    {
    	try
    	{
        	AdmUsers admuser = (AdmUsers) session.getAttribute("admuser");
        	if(null != admuser)
        	{
        		String realName = CookieUtils.getCookie(request, "realName");
        		model.addAttribute("realName", URLDecoder.decode(realName, "utf-8"));
        		return "admin/home.html";
        	}
        	else
        	{
        		this.realLogOut(request, response);
        	}
        	response.sendRedirect("/login");
    	}
    	catch(Exception e)
    	{
    		logger.error("进入后台系统首页出错:{}", e);
    		try 
    		{
    			this.realLogOut(request, response);
				response.sendRedirect("/login");
			} 
    		catch (IOException o) {
    			logger.error("error{}：" + o);
			}
    	}
    	return null;
    }
    
    private void delSessionAndCookies(HttpServletRequest request, HttpServletResponse response)
    {
    	HttpSession session = request.getSession();
    	if(null != session)
    	{
    		session.removeAttribute("admuser");
    	}
    	CookieUtils.delCookie(request, response, "realName");
    }
    
    /** SHIRO logout and session and cookie logout **/
	private void realLogOut(HttpServletRequest request, HttpServletResponse response)
    {
    	Subject subject = SecurityUtils.getSubject();
    	subject.logout();
		this.delSessionAndCookies(request, response);
    }
    
}
