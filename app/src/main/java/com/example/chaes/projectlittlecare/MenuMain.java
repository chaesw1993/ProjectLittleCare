package com.example.chaes.projectlittlecare;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.chaes.projectlittlecare.LCClass.UnPFlag;

public class MenuMain extends AppCompatActivity {

    private TextView mTextMessage;
    UnPFlag u = new UnPFlag();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_request:
                    mTextMessage.setText(R.string.title_request);
                    return true;
                case R.id.navigation_matching:
                    mTextMessage.setText(R.string.title_matching);
                    return true;
                case R.id.navigation_activity:
                    mTextMessage.setText(R.string.title_activity);
                    return true;
            }

            /*
            if(u.getUnPFlag() == 1){ // 제공자
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        mTextMessage.setText(R.string.title_home);
                        return true;
                    case R.id.navigation_request:
                        mTextMessage.setText(R.string.title_request);
                        return true;
                    case R.id.navigation_matching:
                        mTextMessage.setText(R.string.title_matching);
                        return true;
                    case R.id.navigation_activity:
                        mTextMessage.setText(R.string.title_activity);
                        return true;
                }
            } else if(u.getUnPFlag() == 2){ // 유저
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        mTextMessage.setText(R.string.title_home);
                        return true;
                    case R.id.navigation_map:
                        mTextMessage.setText(R.string.title_map);
                        return true;
                    case R.id.navigation_matching:
                        mTextMessage.setText(R.string.title_matching);
                        return true;
                    case R.id.navigation_matching_info:
                        mTextMessage.setText(R.string.title_matching_info);
                        return true;
                }
            }

            */
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        //u.setUnPFlag(1);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}

