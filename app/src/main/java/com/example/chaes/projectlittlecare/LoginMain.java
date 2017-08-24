package com.example.chaes.projectlittlecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.chaes.projectlittlecare.LCClass.FindInfo;
import com.example.chaes.projectlittlecare.LCClass.SignUp;
import com.example.chaes.projectlittlecare.LCClass.UnPFlag;


public class LoginMain extends AppCompatActivity {
    UnPFlag user = new UnPFlag();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);


        Button btn = (Button) findViewById(R.id.btn_login);
        Button btn2 = (Button) findViewById(R.id.btn_login2);
        TextView textView = (TextView) findViewById(R.id.link_signup);
        TextView textView2 = (TextView) findViewById(R.id.link_find);

        btn.setOnClickListener(new View.OnClickListener() { // 제공자
            @Override
            public void onClick(View v) {
                user.setUnPFlag(2);
                Intent intent = new Intent(LoginMain.this, MenuMain.class);
                intent.putExtra("UnPFlag", user);
                startActivity(intent);

                //finish();

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() { // 유저
            @Override
            public void onClick(View v) {
                user.setUnPFlag(1);
                Intent intent = new Intent(LoginMain.this, MenuMain.class);
                intent.putExtra("UnPFlag", user);
                startActivity(intent);
                //finish();
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginMain.this, SignUp.class);
                startActivity(intent);
                //finish();

            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginMain.this, FindInfo.class);
                startActivity(intent);
                //finish();

            }
        });
    }
}
