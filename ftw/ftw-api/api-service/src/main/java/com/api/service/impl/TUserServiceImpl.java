package com.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.api.dao.TestUserDAO;
import com.api.entity.result.TUserJsonResult;
import com.api.service.TUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.util.page.PageParam;
import com.util.page.ResultJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TUserServiceImpl implements TUserService {

    Logger logger = LoggerFactory.getLogger(TUserServiceImpl.class);

    private final TestUserDAO tuserdao;

    @Autowired
    public TUserServiceImpl(TestUserDAO tuserdao) {
        this.tuserdao = tuserdao;
    }

    @Override
    public ResultJson listUser(PageParam pageParam) {
        logger.info("执行分页是否起作用......" + JSONObject.toJSONString(pageParam));
        PageHelper.startPage(pageParam.getPageNo(), pageParam.getPageSize());
        List<TUserJsonResult> tUserJsonResults = tuserdao.listUser();
        PageInfo<TUserJsonResult> datas = new PageInfo<>(tUserJsonResults);
        JSONObject json = new JSONObject();
        json.put("total",  datas.getTotal());
        json.put("pageNo",  pageParam.getPageNo());
        json.put("pageSize",  pageParam.getPageSize());
        json.put("list", tUserJsonResults);
        return ResultJson.ok(json);
    }
}
