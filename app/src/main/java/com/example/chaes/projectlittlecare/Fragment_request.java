package com.example.chaes.projectlittlecare;

/**
 * Created by chaes on 2017-05-26.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.chaes.projectlittlecare.LCClass.ListViewAdapter2;
import com.example.chaes.projectlittlecare.LCClass.ListViewItem2;

public class Fragment_request extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request, container, false);

        ListView listview2;
        ListViewAdapter2 adapter;

        adapter = new ListViewAdapter2();

        listview2 = (ListView)view.findViewById(R.id.listview2);
        listview2.setAdapter(adapter);

        adapter.addItem("Jay Kim", "2016/02/24", "4:30 PM", "3 Hours", "01026725492", "Need help at KPU");
        adapter.addItem("Mike Lee", "2016/03/24", "5:30 PM", "2 Hours", "01055854472", "I need to go to my parent's house");

        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewItem2 item = (ListViewItem2) parent.getItemAtPosition(position);

                String descNameStr = item.getDescNameStr();
                String descDateStr = item.getDescDateStr();
                String descTimeStr = item.getDescTimeStr();
                String descDurationStr = item.getDescDurationStr();
                String descPhoneStr = item.getDescPhoneStr();
                String descDetailStr = item.getDescDetailStr();
            }
        });

        return view;
    }
}