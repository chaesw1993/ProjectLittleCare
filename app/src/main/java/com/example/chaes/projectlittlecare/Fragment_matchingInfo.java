package com.example.chaes.projectlittlecare;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.chaes.projectlittlecare.LCClass.ListViewAdapter;
import com.example.chaes.projectlittlecare.LCClass.ListViewItem;

public class Fragment_matchingInfo extends Fragment {

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        View view = inflater.inflate( R.layout.fragment_matching_info, container, false );


        ListView listview;
        ListViewAdapter adapter;

        adapter = new ListViewAdapter();

        listview = (ListView)view.findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        adapter.addItem("Name: ", "Jay Kim");
        adapter.addItem("Place: ", "Seoul");
        adapter.addItem("Date: ", "2017/05/20");
        adapter.addItem("Time Duration: ", "3 hours");
        adapter.addItem("Type: ", "Blind");
        adapter.addItem("Comment: ", "05-27 14:26:42.152 2356-2364/? W/SQLiteConnectionPool: " +
                "A SQLiteConnection object for database '/data/user/0/com.google.android.gms" +
                "/databases/auto_complete_suggestions.db' was leaked!  " +
                "Please fix your application to end transactions in progress properly and " +
                "to close the database when it is no longer needed.");

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);

                String titleStr = item.getTitle();
                String descStr = item.getDesc();
            }
        });

        return view;
    }
}