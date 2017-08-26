package com.example.chaes.projectlittlecare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.chaes.projectlittlecare.LCClass.CenterMatchingAdapter;
import com.example.chaes.projectlittlecare.LCClass.CenterMatchingItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchingList extends AppCompatActivity {

    private TextView selected_item_textview;
    private static final String URL = "http://52.79.162.197/load_data.php";
    private RequestQueue requestQueue;
    private StringRequest request;
    String user_name;
    List<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_list);

        ListView listView = (ListView)findViewById(R.id.listview);
        selected_item_textview = (TextView)findViewById(R.id.selected_item_textview);
        //name = (TextView)findViewById(R.id.textView_list_name);
        //phone = (TextView)findViewById(R.id.textView_list_phone);

        requestQueue = Volley.newRequestQueue(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {

                //클릭한 아이템의 문자열을 가져옴
                String selected_item = (String)adapterView.getItemAtPosition(position);
                //String selected_item = (String)adapterView.getItemAtPosition(position);

                //텍스트뷰에 출력
                selected_item_textview.setText(selected_item);
                //phone.setText(selected_item);
            }
        });
        //리스트뷰에 보여질 아이템을 추가

        request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("email");
                    if(jsonArray != null) {
                        for (int i=0; i<jsonArray.length(); i++){
                            list.add(jsonArray.getString(i));
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to Upload", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                //hashMap.put("role", "provider");
                return hashMap;
            }
        };
        requestQueue.add(request);
    }
}
