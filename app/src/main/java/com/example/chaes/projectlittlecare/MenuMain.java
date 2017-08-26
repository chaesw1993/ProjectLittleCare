package com.example.chaes.projectlittlecare;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.chaes.projectlittlecare.LCClass.UnPFlag;

public class MenuMain extends AppCompatActivity {

    UnPFlag user = new UnPFlag();
    //Intent intent = getIntent();
    //String email = intent.getStringExtra("email");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        Intent intent = getIntent();
        user = (UnPFlag)intent.getSerializableExtra("UnPFlag");
        String email = intent.getStringExtra("email");

        Button btn1 = (Button)findViewById(R.id.all_btn1);
        Button btn2 = (Button)findViewById(R.id.all_btn2);
        Button btn3 = (Button)findViewById(R.id.all_btn3);
        Button btn4 = (Button)findViewById(R.id.all_btn4);

        btn1.setOnClickListener(LCClickListener);
        btn2.setOnClickListener(LCClickListener);
        btn3.setOnClickListener(LCClickListener);
        btn4.setOnClickListener(LCClickListener);


    }
    Button.OnClickListener LCClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.all_btn1){    // 내정보
                Intent intent = new Intent(MenuMain.this, MyInfo.class);
                startActivity(intent);
            } else if(v.getId() == R.id.all_btn2){ // 지도
                Intent intent = new Intent(MenuMain.this, MapsActivity.class);
                startActivity(intent);
            } else if(v.getId() == R.id.all_btn3){ // 매칭
                //Intent intent = new Intent(MenuMain.this, MenuMain.class);
                //startActivity(intent);
            } else if(v.getId() == R.id.all_btn4){ // 앱종료
                finish();
            }
        }
    };
}

