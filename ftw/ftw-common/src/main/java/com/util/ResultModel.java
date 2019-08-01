package com.util;



public class ResultModel<T> {

	private Integer code;

	/*
     结果数据
   */
    private T data;

    /*
           异常时提示信息
    */
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultModel()
    {
        //默认成功
        code = 200;
    }
    public ResultModel(Integer code, String msg)
    {
        //默认成功
        this.code = code;
        this.msg=msg;
    }

    public static <E> ResultModel<E> success(E data){
        ResultModel<E> r=new ResultModel<E>();
        r.setCode(200);
        r.setData(data);
        r.setMsg("成功");
        return r;
    }
    public static <E> ResultModel<E> error(int code,String msg){
        ResultModel<E> r=new ResultModel<E>();
        r.setCode(code);
        r.setData(null);
        r.setMsg(msg);
        return r;
    }
}
