package com.util.page;

import com.util.DateTools;
import com.util.ErrorCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ResultJson<T> {

    public static final int OK = ErrorCode.OK.getReturncode();

    private static final int ERROR = ErrorCode.ERROR.getReturncode();

    @ApiModelProperty("状态码，0为正常，错误码参考ErrorCode")
    private int returnCode;

    @ApiModelProperty("结果描述")
    private String message;

    @ApiModelProperty("时间戳")
    private String timeStamp;

    @ApiModelProperty("结果对象")
    private T result;

    public static <T> ResultJson<T> ok(T data) {
        ResultJson<T> rj = new ResultJson<>();
        rj.returnCode = OK;
        rj.message = "OK";
        rj.timeStamp = DateTools.DateToStr(new Date(), DateTools.DEFAULTPATTERN);
        rj.result = data;
        return rj;
    }

    public static <T> ResultJson<T> badRequest() {
        ResultJson<T> rj = new ResultJson<>();
        rj.returnCode = ERROR;
        rj.message = ErrorCode.ERROR.getMessage();
        rj.timeStamp = DateTools.DateToStr(new Date(), DateTools.DEFAULTPATTERN);
        rj.result = null;
        return rj;
    }

    public static <T> ResultJson<T> getBadRequest(int code, String message) {
        ResultJson<T> rj = new ResultJson<>();
        rj.returnCode = code;
        rj.message = message;
        rj.timeStamp = DateTools.DateToStr(new Date(), DateTools.DEFAULTPATTERN);
        rj.result = null;
        return rj;
    }




}
