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

import com.example.chaes.projectlittlecare.LCClass.UnPFlag;

public class MenuMain extends AppCompatActivity {

    UnPFlag user = new UnPFlag();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        Fragment fragment;
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if(user.getUnPFlag() == 1){ // 제공자
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        fragment = new Fragment_myInfo();
                        break;
                    case R.id.navigation_request:
                        fragment = new Fragment_request();
                        break;
                    case R.id.navigation_matching:
                        fragment = new Fragment_matching();
                        break;
                    case R.id.navigation_activity:
                        fragment = new Fragment_activity();
                        break;
                }
            } else if(user.getUnPFlag() == 2){ // 유저
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        fragment = new Fragment_myInfo();
                        break;
                    case R.id.navigation_map:
                        Intent intent = new Intent(MenuMain.this, MapsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_matching:
                        fragment = new Fragment_matching();
                        break;
                    case R.id.navigation_matching_info:
                        fragment = new Fragment_matchingInfo();
                        break;
                }
            }
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace( R.id.fragment_place, fragment);
            fragmentTransaction.commit();
            return false;
        }

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        Intent intent = getIntent();
        user = (UnPFlag)intent.getSerializableExtra("UnPFlag");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationView navigation2 = (BottomNavigationView) findViewById(R.id.navigation2);

        if(user.getUnPFlag() == 1){ // 제공자
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
            navigation2.setVisibility(View.GONE);
        } else{ // 유저
            navigation2.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
            navigation.setVisibility(View.GONE);
        }
        Fragment fragment = new Fragment_myInfo();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.fragment_place, fragment);
        fragmentTransaction.commit();
    }

}

