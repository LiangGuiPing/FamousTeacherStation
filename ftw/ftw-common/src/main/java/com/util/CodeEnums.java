package com.util;

public enum CodeEnums {
    SUCCESS(200, "成功"),
    ERROR(500, "系统异常"),
    NAUTHORIZED(401, "未授权"),
    DENYACCESS(403, "禁止访问"),
    NOTFIND(404, "您访问的资源已经不存在了"),
    //自定义的错误码建议从10001开始
    ;
    private Integer code;
    private String msg;

    private CodeEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
