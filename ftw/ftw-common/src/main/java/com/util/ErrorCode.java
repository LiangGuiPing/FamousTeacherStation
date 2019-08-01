package com.util;

public enum ErrorCode {
    OK(0, "成功"),
    ERROR(999, "服务器异常"),
    ;
    private final int returnCode;
    private final String message;

    ErrorCode(int returnCode, String message) {
        this.returnCode = returnCode;
        this.message = message;
    }

    public int getReturncode() {
        return this.returnCode;
    }

    public String getMessage() {
        return this.message;
    }
}
