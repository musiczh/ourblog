package com.example.ourblog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.example.ourblog.view.activity.BaseActivity;
import com.example.ourblog.viewmodel.MainActViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * @author singsong
 */
public class MainActivity extends BaseActivity {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    public static Context getContext(){
        return context;
    }

    private MainActViewModel mViewModel;
    private NavController mNavController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewModel = new ViewModelProvider(this).get(MainActViewModel.class);
        context = getApplicationContext();

    }

    @Override
    protected void onStart() {
        super.onStart();
        //在onCreate里尚未创加载碎片，无法获取navController
        mNavController = Navigation.findNavController(this,R.id.main_fragment_container);
    }

    /**
     * 主要负责获取view的实例，统一初始化。
     */
    private void viewBinding(){
        //底部导航栏的初始化
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.main_page:
                        mNavController.navigate(R.id.mainFragment);
                        return true;
                    case R.id.blog_page:
                        mNavController.navigate(R.id.blogFragment);
                        return true;
                    case R.id.my_page:
                        mNavController.navigate(R.id.myFragment);
                        return true;
                    default:return false;
                }
            }
        });
    }

    public NavController getNavController() {
        return mNavController;
    }




}
