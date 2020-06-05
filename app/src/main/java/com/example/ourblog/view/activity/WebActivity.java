package com.example.ourblog.view.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ourblog.R;


public class WebActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        //内置一个浏览器
        WebView webView=findViewById(R.id.web_view);
        WebSettings webSettings=webView.getSettings();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW); }///////////////////////
        //让webview支持javaScript脚本
        webSettings.setJavaScriptEnabled(true);
        //当跳转到另外一个网页任然在APP里显示而不是打开浏览器
        webView.setWebViewClient(new WebViewClient());
        //设置网页自适应屏幕，还有可以缩放，隐藏自带的缩放按钮
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webView.loadUrl(getIntent().getStringExtra("link"));
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
    }
}
