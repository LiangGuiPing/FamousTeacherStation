package com.util.page;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;

public class ResultEntity {

    private Integer returncode;

    private String message;

    private Object result;

    public Integer getReturncode() {
        return returncode;
    }

    void setReturncode(Integer returncode) {
        this.returncode = returncode;
    }

    String getMessage() {
        return message;
    }

    void setMessage(String message) {
        this.message = message;
    }
    public void setResponseCode(ResponseCode responseCode) {
        this.returncode=responseCode.getCode();
        this.message=responseCode.getMessage();
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public static ResultEntity getSuccessResult(ResultEntity resultEntity){
        resultEntity.setReturncode(0);
        resultEntity.setMessage(StringUtils.isBlank(resultEntity.getMessage()) ||  resultEntity.getMessage().equals(ResponseCode.FAILURE.getMessage())? ResponseCode.SUCCESS.getMessage() : resultEntity.getMessage());
        resultEntity.setResult(resultEntity.getResult());
        return resultEntity;
    }

    public static ResultEntity getSuccessResult(){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setReturncode(0);
        resultEntity.setMessage(StringUtils.isBlank(resultEntity.getMessage()) || resultEntity.getMessage().equals(ResponseCode.FAILURE.getMessage()) ? ResponseCode.SUCCESS.getMessage() : resultEntity.getMessage());
        return resultEntity;
    }
    public static ResultEntity getErrorResult(){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setReturncode(ResponseCode.FAILURE.getCode());
        resultEntity.setMessage(ResponseCode.FAILURE.getMessage());
        return resultEntity;
    }
    public static ResultEntity getErrorResult(ResponseCode responseCode){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setReturncode(responseCode.getCode());
        resultEntity.setMessage(responseCode.getMessage());
        return resultEntity;
    }
    public static ResultEntity getErrorResult(String message){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setMessage(message);
        resultEntity.setReturncode(999);
        return resultEntity;
    }
    public static ResultEntity getSuccessResult(String message,Object result){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setReturncode(0);
        resultEntity.setMessage(message);
        resultEntity.setResult(result);
        return resultEntity;
    }
    public static ResultEntity getResult(Integer returncode,String message){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setReturncode(returncode);
        resultEntity.setMessage(message);
        return resultEntity;
    }

	public static ResultEntity getPageResult(PageInfo pageInfo){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setReturncode(0);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("info", pageInfo.getList());
        jsonObject.put("total", pageInfo.getTotal());
        jsonObject.put("pageSize", pageInfo.getPageSize());
        resultEntity.setResult(jsonObject);
        return resultEntity;
    }

}
