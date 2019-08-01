package com.util.token;

import lombok.Data;

@Data
public class LoginInfoEntity {

    private Integer userId;

    private String userName;

    private boolean isLogin;
    
    /**
     * 平台类型：T或P或其它平台字符串
     */
    private String platForm;

    /**
     * 当前时间
     */
    private Long currentTime;

}