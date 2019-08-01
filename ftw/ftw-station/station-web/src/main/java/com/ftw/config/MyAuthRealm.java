package com.ftw.config;

import com.ftw.entity.adm.AdmUsers;
import com.ftw.service.AdmUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

public class MyAuthRealm extends AuthorizingRealm
{  
	@Resource
    private AdmUserService admuserservice;
    
    @Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) 
    {  
    	System.out.println("我被执行到了：{}");
    	String mobile = (String) principalCollection.fromRealm(getName()).iterator().next(); 
    	AdmUsers user = admuserservice.findUserByLoginName(mobile);
        if(user != null)
        {  
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  
            Set<String> roles = admuserservice.getRoles(user);            
            info.setRoles(roles);
            List<String> permissions = admuserservice.getPermissionsByRoleId(user);
            
            if(null != permissions && permissions.size() > 0)
            {
            	for(String s : permissions)
            	{
            		info.addStringPermission(s);
            	}
            }
            return info;  
        }  
        return null;  
    }  
  
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException 
	{
		System.out.println("登录时调用到了：{}");
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;  
		AdmUsers user = admuserservice.findUserByLoginName(token.getUsername());
        if(user != null)
        {
        	String password = user.getPassword();
        	return new SimpleAuthenticationInfo(user.getLoginname(), password, getName());
        }  
        return null;  
	}  
	
}  