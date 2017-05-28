package com.example.chaes.projectlittlecare.LCClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chaes.projectlittlecare.R;

import java.util.ArrayList;

public class ListViewAdapter2 extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListViewItem2> listViewItemList2 = new ArrayList<ListViewItem2>() ;

    // ListViewAdapter의 생성자
    public ListViewAdapter2() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList2.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item2, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView descNameTextView = (TextView) convertView.findViewById(R.id.list2_descName) ;
        TextView descDateTextView = (TextView) convertView.findViewById(R.id.list2_descDate) ;
        TextView descTimeTextView = (TextView) convertView.findViewById(R.id.list2_descTime) ;
        TextView descDurationTextView = (TextView) convertView.findViewById(R.id.list2_descDuration) ;
        TextView descPhoneTextView = (TextView) convertView.findViewById(R.id.list2_descPhone) ;
        TextView descDetailTextView = (TextView) convertView.findViewById(R.id.list2_descDetail) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItem2 listViewItem2 = listViewItemList2.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        descNameTextView.setText(listViewItem2.getDescNameStr());
        descDateTextView.setText(listViewItem2.getDescDateStr());
        descTimeTextView.setText(listViewItem2.getDescTimeStr());
        descDurationTextView.setText(listViewItem2.getDescDurationStr());
        descPhoneTextView.setText(listViewItem2.getDescPhoneStr());
        descDetailTextView.setText(listViewItem2.getDescDetailStr());

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList2.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem( String descName, String descDate, String descTime, String descDuration,
                         String descPhone, String descDetail) {
        ListViewItem2 item = new ListViewItem2();

        item.setDescNameStr(descName);
        item.setDescDateStr(descDate);
        item.setDescTimeStr(descTime);
        item.setDescDurationStr(descDuration);
        item.setDescPhoneStr(descPhone);
        item.setDescDetailStr(descDetail);

        listViewItemList2.add(item);
    }
}

