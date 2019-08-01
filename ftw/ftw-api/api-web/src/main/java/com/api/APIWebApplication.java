package com.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@ComponentScan(basePackages = {"com"})
@MapperScan(basePackages = {"com.api.dao"})
@EnableTransactionManagement
public class APIWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(APIWebApplication.class, args);
    }

}
