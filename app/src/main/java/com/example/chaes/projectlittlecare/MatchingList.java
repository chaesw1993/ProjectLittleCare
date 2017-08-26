package com.example.chaes.projectlittlecare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.chaes.projectlittlecare.LCClass.CenterMatchingAdapter;
import com.example.chaes.projectlittlecare.LCClass.CenterMatchingItem;

public class MatchingList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_center_matching);

        ListView center_list;
        CenterMatchingAdapter adapter;

        adapter = new CenterMatchingAdapter();

        center_list = (ListView)findViewById(R.id.center_list);
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
    }
}
