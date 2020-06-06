package com.example.ourblog.model.entity;

/**
 * @author singsong
 * 耗时任务接口回调
 */
public interface MyCallBack<T> {

    /**
     * 请求成功调用此方法
     * @param data 请求得到的数据
     */
    void onSucceed(T data);

    /**
     * 请求失败调用方法
     * @param msg 错误信息
     */
    void onFailed(String msg);
}
