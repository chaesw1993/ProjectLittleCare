package com.example.chaes.projectlittlecare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyInfo extends AppCompatActivity {

    private static final String URL = "http://52.79.162.197/user_info.php";
    private RequestQueue requestQueue;
    private StringRequest request;
    private EditText id, name, birth, phone;
    private TextView user_profile_name;
    private Button button;
    String user_email, user_name, user_birth, user_phone;
    InputStream is = null;
    String result = null;
    //String email = "jaehyoung94@naver.com";
            //intent.getStringExtra("email");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        button = (Button) findViewById(R.id.Modify);
        id = (EditText) findViewById(R.id.user_id);
        name = (EditText) findViewById(R.id.user_name);
        birth = (EditText) findViewById(R.id.user_age);
        phone = (EditText) findViewById(R.id.user_phone_number);
        user_profile_name = (TextView)findViewById(R.id.Username);

        requestQueue = Volley.newRequestQueue(this);

        request =  new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    user_email = (jsonObject.getString("email"));
                    user_name = (jsonObject.getString("name"));
                    user_birth = (jsonObject.getString("birth"));
                    user_phone = (jsonObject.getString("phone"));
                    id.setText(user_email, TextView.BufferType.EDITABLE);
                    name.setText(user_name, TextView.BufferType.EDITABLE);
                    birth.setText(user_birth, TextView.BufferType.EDITABLE);
                    phone.setText(user_phone, TextView.BufferType.EDITABLE);
                    user_profile_name.setText(user_name, TextView.BufferType.EDITABLE);
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
                Intent intent = getIntent();
                String email = intent.getStringExtra("email");
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("email", email);

                return hashMap;
            }
        };

        requestQueue.add(request);

    }
}
