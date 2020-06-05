package com.example.ourblog;

public interface CallBack<T> {

        void success(T t);
        void failed(String msg);
}
