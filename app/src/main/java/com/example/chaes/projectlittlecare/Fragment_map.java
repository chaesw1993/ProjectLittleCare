package com.example.chaes.projectlittlecare;

/**
 * Created by chaes on 2017-05-26.
 */

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


// 수정 많이 필요
public class Fragment_map extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        ((Button) view.findViewById(R.id.chooseCenter)).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

    }
}