//package com.util.config;
//
//import com.github.pagehelper.PageHelper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Properties;
//
//@Configuration
//public class PageHelperConfig {
//
//    Logger logger = LoggerFactory.getLogger(PageHelperConfig.class);
//
//    @Bean
//    public PageHelper getPageHelper(){
//        logger.info("加载PageHelper配置项......");
//        PageHelper pageHelper=new PageHelper();
//        Properties properties=new Properties();
//        properties.setProperty("helperDialect","mysql");
//        properties.setProperty("reasonable","true");
//        properties.setProperty("supportMethodsArguments","true");
//        properties.setProperty("params","count=countSql");
//        pageHelper.setProperties(properties);
//        return pageHelper;
//    }
//
//}