package com.example.ourblog.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.ourblog.R;
import com.example.ourblog.model.Reposity;
import com.example.ourblog.model.entity.CallBack;
import com.example.ourblog.model.network.netbean.RegisterMsg;
import com.google.android.material.textfield.TextInputEditText;

/**
 * @author singsong
 */
public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        viewInit();
    }

    private void f(){
        finish();
    }

    private void viewInit(){
        final TextInputEditText usernameText = findViewById(R.id.textInputEditText_username);
        final TextInputEditText passwordText = findViewById(R.id.textInputEditText_password);
        final TextInputEditText emailText = findViewById(R.id.textInputEditText_email);
        final TextInputEditText phoneText = findViewById(R.id.textInputEditText_phone);


        Button registerButton = findViewById(R.id.button_register);
        registerButton.setOnClickListener(v -> {
            String username = String.valueOf(usernameText.getText());
            String password = String.valueOf(passwordText.getText());
            String phone = String.valueOf(phoneText.getText());
            String email = String.valueOf(emailText.getText());

            if (username.isEmpty()){ usernameText.setError("用户名不能为空"); }
            else if (password.isEmpty()){ passwordText.setError("密码不能为空"); }
            else if (email.isEmpty()){ emailText.setError("邮箱不能为空"); }
            else if (phone.isEmpty()){ phoneText.setError("手机号不能为空"); }
            else{
                RegisterMsg registerMsg = new RegisterMsg(username,password,email,phone,"null");
                Reposity.getInstance().register(registerMsg, new CallBack<String>() {
                    @Override
                    public void onSucceed(String data) {
                        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailed(String msg) {
                        Toast.makeText(RegisterActivity.this,msg,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
