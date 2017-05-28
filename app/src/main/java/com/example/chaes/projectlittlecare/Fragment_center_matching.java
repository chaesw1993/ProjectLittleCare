package com.example.chaes.projectlittlecare;

/**
 * Created by chaes on 2017-05-28.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.chaes.projectlittlecare.LCClass.CenterMatchingAdapter;
import com.example.chaes.projectlittlecare.LCClass.CenterMatchingItem;

// 오류
public class Fragment_center_matching extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_center_matching, container, false);
/*
        ListView center_list;
        CenterMatchingAdapter adapter;

        adapter = new CenterMatchingAdapter();

        center_list = (ListView)view.findViewById(R.id.center_list);
        center_list.setAdapter(adapter);

        adapter.addItem(R.drawable.logo, "홍길동", "43");
        adapter.addItem(R.drawable.person3, "김철수", "49");

        center_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CenterMatchingItem item = (CenterMatchingItem) parent.getItemAtPosition(position);

                int image = item.getImage();
                String name = item.getName();
                int age = Integer.parseInt(item.getAge());
            }
        });
*/
        return view;
    }
}