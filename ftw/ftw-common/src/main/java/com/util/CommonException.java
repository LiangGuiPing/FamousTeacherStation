package com.util;


public class CommonException extends  RuntimeException {

    private Integer code;

    public CommonException(CodeEnums codeEnums) {

        super(codeEnums.getMsg());
        this.code = codeEnums.getCode();
    }
    public CommonException(Exception e) {

        super(e.getMessage());
        this.code = 500;
    }
    public CommonException(String msg) {

        super(msg);
        this.code = 500;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
