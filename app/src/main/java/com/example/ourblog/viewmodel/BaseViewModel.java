package com.example.ourblog.viewmodel;

import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {


    public interface CallBack<T> {

        void success(T t);
        void failed(String msg);
    }
}
