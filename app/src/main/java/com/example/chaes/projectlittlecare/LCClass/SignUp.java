package com.example.chaes.projectlittlecare.LCClass;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.chaes.projectlittlecare.LoginMain;
import com.example.chaes.projectlittlecare.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    private static final String URL = "http://52.79.162.197/insertUser.php";
    private RequestQueue requestQueue;
    private StringRequest request;
    private EditText email, password, birth, name, phone;
    private Button button;
    private RadioButton user, provider;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        button = (Button) findViewById(R.id.button);
        email = (EditText) findViewById(R.id.sign_id);
        password = (EditText) findViewById(R.id.sign_pw);
        name = (EditText) findViewById(R.id.sign_name);
        birth = (EditText) findViewById(R.id.sign_birth);
        phone = (EditText) findViewById(R.id.sign_phone);
        user = (RadioButton)findViewById(R.id.disability);
        provider = (RadioButton)findViewById(R.id.provider);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                role = "user";
            }
        });

        provider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                role = "provider";
            }
        });

        requestQueue = Volley.newRequestQueue(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.names().get(0).equals("success")) {
                                Toast.makeText(getApplicationContext(), "계정생성 성공" + jsonObject.getString("success"),
                                        Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), LoginMain.class));
                            } else {
                                Toast.makeText(getApplicationContext(), "계정생성 실패" + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
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
                        hashMap.put("email", email.getText().toString());
                        hashMap.put("password", password.getText().toString());
                        hashMap.put("name", name.getText().toString());
                        hashMap.put("birth", birth.getText().toString());
                        hashMap.put("phone", phone.getText().toString());
                        hashMap.put("role", role);

                        return hashMap;
                    }
                };
                requestQueue.add(request);
            }
            });
        }
}