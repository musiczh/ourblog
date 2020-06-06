package com.example.ourblog.model.network.netbean;

/**
 * @author singsong
 * 基础bean类
 */
public class BaseBean<T> {
    private int code;
    private String msg;
    private T data;
    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }

    public void setData(T data) {
        this.data = data;
    }
    public T getData() {
        return data;
    }



}
