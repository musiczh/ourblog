package com.example.ourblog.view.activity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ourblog.MainActivity;
import com.example.ourblog.R;
import com.example.ourblog.model.Reposity;
import com.example.ourblog.model.entity.MyCallBack;
import com.example.ourblog.model.network.netbean.LoginData;
import com.google.android.material.textfield.TextInputEditText;

/**
 * @author singsong
 */
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewInit();
    }

    private void viewInit(){
        final TextInputEditText usernameText = findViewById(R.id.textInputEditText_username);
        final TextInputEditText passwordText = findViewById(R.id.textInputEditText_password);

        TextView gotoRegisterTextView = findViewById(R.id.textView_goto_register);
        gotoRegisterTextView.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.activity_start_anim,R.anim.activity_finish_anim);
        });

        Button loginButton = findViewById(R.id.button_login);
        loginButton.setOnClickListener(v -> {
            String username = String.valueOf(usernameText.getText());
            String password = String.valueOf(passwordText.getText());
            if (!username.isEmpty() && !password.isEmpty()){
                Reposity.getInstance().login(username, password, new MyCallBack<LoginData>() {
                    @Override
                    public void onSucceed(LoginData data) {
                        MainActivity.loginData = data;
                        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailed(String msg) {
                        Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                if (username.isEmpty()){ usernameText.setError("用户名不能为空");}
                if (password.isEmpty()){ passwordText.setError("密码不能为空"); }
            }
        });


    }
}
