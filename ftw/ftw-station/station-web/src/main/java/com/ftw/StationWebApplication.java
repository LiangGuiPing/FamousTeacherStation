package com.ftw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// @ImportResource("classpath:spring-shiro.xml")
@SpringBootApplication
@EnableWebMvc
@ComponentScan(basePackages = {"com"})
@MapperScan(basePackages = {"com.ftw.dao"})
@EnableTransactionManagement
public class StationWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(StationWebApplication.class, args);
    }

}
