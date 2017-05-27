package com.example.chaes.projectlittlecare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chaes.projectlittlecare.LCClass.UnPFlag;


public class LoginMain extends AppCompatActivity {
    UnPFlag user = new UnPFlag();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);


        Button btn = (Button)findViewById(R.id.btn_login);
        Button btn2 = (Button)findViewById(R.id.btn_login2);

        btn.setOnClickListener(new View.OnClickListener(){ // 제공자
            @Override
            public void onClick(View v) {
                user.setUnPFlag(1);
                Intent intent = new Intent(LoginMain.this, MenuMain.class);
                intent.putExtra("UnPFlag", user);
                startActivity(intent);
                finish();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() { // 유저
            @Override
            public void onClick(View v) {
                user.setUnPFlag(2);
                Intent intent = new Intent(LoginMain.this, MenuMain.class);
                intent.putExtra("UnPFlag", user);
                startActivity(intent);
                finish();
            }
        });
    }
}
