package com.api.controller;

import com.alibaba.fastjson.JSON;
import com.api.service.TUserService;
import com.util.page.PageParam;
import com.util.page.ResultJson;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Api(tags = "测试用户接口服务", description = "测试用户接口服务", hidden = false)
@RestController
@RequestMapping("/")
public class TestUserController {

    Logger logger = LoggerFactory.getLogger(TestUserController.class);

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private TUserService tuserservice;

    @GetMapping("/")
    public String index() {
        return "<h2><a href='/doc.html'>欢迎访问API服务</a></h2>";
    }

    @PostMapping("/list")
    public ResultJson list(PageParam param) {
        ResultJson resultJson = ResultJson.badRequest();
        try {
            resultJson = tuserservice.listUser(param);
            redisTemplate.opsForValue().set("test:user:", JSON.toJSONString(resultJson), 5, TimeUnit.MINUTES);
        } catch (Exception e) {
            logger.error("获取测试用户接口出错{} ：" , e);
        }
        return resultJson;
    }


}
