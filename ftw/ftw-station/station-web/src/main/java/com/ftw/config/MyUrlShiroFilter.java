package com.ftw.config;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;

@Configuration
public class MyUrlShiroFilter
{
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager)
	{
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(manager);
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/logout", "anon");
		filterChainDefinitionMap.put("/do", "anon");
		filterChainDefinitionMap.put("/druid/*", "anon");
		filterChainDefinitionMap.put("/css/*.*", "anon");
		filterChainDefinitionMap.put("/fonts/*.*", "anon");
		filterChainDefinitionMap.put("/images/*.*", "anon");
		filterChainDefinitionMap.put("/js/*.*", "anon");
		filterChainDefinitionMap.put("/plugins/laydate/*.*", "anon");
		filterChainDefinitionMap.put("/plugins/laydate/fonts/*.*", "anon");
		filterChainDefinitionMap.put("/viewjs/login/*.*", "anon");

		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/*", "authc");
		filterChainDefinitionMap.put("/*.*", "authc");
		filterChainDefinitionMap.put("/**", "authc");
		filterChainDefinitionMap.put("/*/*", "authc");
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		bean.setLoginUrl("/login");
		bean.setSuccessUrl("/home");
		bean.setUnauthorizedUrl("/403");
		return bean;
	}
	
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") MyAuthRealm authRealm)
    {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        return manager;
    }
    
    @Bean(name = "authRealm")
    public MyAuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher)
    {
        MyAuthRealm authRealm = new MyAuthRealm();
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }

    @Bean(name = "credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() 
    {
       return new SimpleCredentialsMatcher();
    }
    
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() 
    {
        return new LifecycleBeanPostProcessor();
    }
    
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator()
    {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
    
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager)
    {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager((SecurityManager) manager);
        return advisor;
    }
}
