package com.example.chaes.projectlittlecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.chaes.projectlittlecare.LCClass.UnPFlag;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MenuMain extends AppCompatActivity {

    private static final String URL = "http://52.79.162.197/user_info.php";
    RequestQueue requestQueue;
    StringRequest request;
    UnPFlag user = new UnPFlag();
    String user_email, user_name;
    private TextView id, name;
    long lastPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        final Intent intent = getIntent();
        user = (UnPFlag) intent.getSerializableExtra("UnPFlag");
        String email = intent.getStringExtra("email");
        user_email = email;

        id = (TextView) findViewById(R.id.myInfoId);
        name = (TextView) findViewById(R.id.myInfoName);

        id.setText(user_email);

        Button btn1 = (Button) findViewById(R.id.all_btn1);
        Button btn2 = (Button) findViewById(R.id.all_btn2);
        Button btn3 = (Button) findViewById(R.id.all_btn3);
        Button btn4 = (Button) findViewById(R.id.all_btn4);
        Button btn5 = (Button) findViewById(R.id.myInfoModify);

        requestQueue = Volley.newRequestQueue(this);

        request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    user_name = (jsonObject.getString("name"));
                    name.setText(user_name);
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

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuMain.this, MyInfo.class);
                intent.putExtra("email", user_email);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuMain.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuMain.this, AAA_matchingTest.class);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                MenuMain.this.finish();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuMain.this, MyInfo.class);
                intent.putExtra("email", user_email);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onBackPressed() {

        if (System.currentTimeMillis() - lastPressed < 1500){
            FirebaseAuth.getInstance().signOut();
            MenuMain.this.finish();
        }
        Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        lastPressed = System.currentTimeMillis();


    }
}

