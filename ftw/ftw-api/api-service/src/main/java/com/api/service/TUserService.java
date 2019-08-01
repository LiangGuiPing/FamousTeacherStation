package com.api.service;

import com.util.page.PageParam;
import com.util.page.ResultJson;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TUserService {
    /**
     * 用户列表
     */
    ResultJson listUser(PageParam pageParam);
}
