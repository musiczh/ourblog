package com.example.ourblog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import com.example.ourblog.view.activity.BaseActivity;

/**
 * @author singsong
 */
public class MainActivity extends BaseActivity {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    public static Context getContext(){
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

    }
}
