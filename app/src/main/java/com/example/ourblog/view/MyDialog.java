package com.example.ourblog.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.example.ourblog.R;

public class MyDialog extends Dialog {

    int mThemeResId;
    Activity mContext;
    View mView;
    LinearLayout mLinearLayout;
    float mPercent;


    public MyDialog(@NonNull Activity context, int themeResId, float percent) {
        super(context, R.style.MyDialog);
        this.mThemeResId=themeResId;
        this.mContext=context;
        this.mPercent=percent;
    }

    public View getView(){
        if(mView==null){
            createView();
        }
        return mView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //提前设置Dialog的一些样式
        Window dialogWindow = getWindow();
        assert dialogWindow != null;
        dialogWindow.setGravity(Gravity.BOTTOM);
        //设置动画效果
        dialogWindow.setWindowAnimations(R.style.normalDialogAnim);

        setContentView(R.layout.dialog);
        mLinearLayout=findViewById(R.id.container);

        WindowManager windowManager = mContext.getWindowManager();
        final DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        // 设置dialog高度为屏幕的2/3
        lp.height = metrics.heightPixels * (int)mPercent;
        lp.width=metrics.widthPixels;
        getWindow().setAttributes(lp);
        //点击外部Dialog消失
        setCanceledOnTouchOutside(true);

        //添加自定义部分
        if (mView == null) {
            createView();
        }
        FrameLayout frameLayout = findViewById(R.id.layout);
        frameLayout.addView(mView);


        //拖动
        findViewById(R.id.title).setOnTouchListener(new View.OnTouchListener() {

            int startY;
            //原本的位置
            int originalTop=0;
            int originalBot=0;


            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // event.getRawX(); //获取手指第一次接触屏幕在x方向的坐标
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:// 获取手指第一次接触屏幕
                        startY = (int) motionEvent.getRawY();
                        if(originalTop==0){
                            originalTop=mLinearLayout.getTop();
                            originalBot=mLinearLayout.getBottom();
                        }

                        break;
                    case MotionEvent.ACTION_MOVE:// 手指在屏幕上移动对应的事件
                        //调用layout方法来重新放置它的位置
                        int move=(int)motionEvent.getRawY()-startY;
                        if(move>0){
                            mLinearLayout.layout(mLinearLayout.getLeft(),originalTop+move,mLinearLayout.getRight(),originalBot+move);
                        }
                        break;
                    case MotionEvent.ACTION_UP:// 手指离开屏幕对应事件
                        if(originalBot/2<(int)motionEvent.getRawY()-startY){
                            MyDialog.this.dismiss();
                        }else{
                            mLinearLayout.layout(mLinearLayout.getLeft(),originalTop,mLinearLayout.getRight(),originalBot);
                        }
                        break;
                     default:
                         break;
                }
                return true;// 不会中断触摸事件的返回
            }
        });
    }

    private void createView(){
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mView=inflater.inflate(mThemeResId, null);
    }
}
