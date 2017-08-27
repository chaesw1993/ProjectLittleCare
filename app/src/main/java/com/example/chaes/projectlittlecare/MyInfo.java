package com.example.chaes.projectlittlecare;

import android.*;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static android.R.attr.bitmap;

public class MyInfo extends AppCompatActivity {

    private static final String URL = "http://52.79.162.197/user_info.php";
    private static final String URL2 = "http://52.79.162.197/user_update.php";
    private RequestQueue requestQueue;
    private StringRequest request;
    private EditText id, name, birth, phone;
    private TextView user_profile_name;
    private Button button;
    String user_email, user_name, user_birth, user_phone;


    private ImageButton imBtn;
    String TAG = getClass().getSimpleName();
    ProgressBar pbLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        imBtn = (ImageButton) findViewById(R.id.user_profile_photo);


        pbLogin = (ProgressBar) findViewById(R.id.progressBar2);


        button = (Button) findViewById(R.id.Modify);
        id = (EditText) findViewById(R.id.user_id);
        name = (EditText) findViewById(R.id.user_name);
        birth = (EditText) findViewById(R.id.user_age);
        phone = (EditText) findViewById(R.id.user_phone_number);
        user_profile_name = (TextView) findViewById(R.id.Username);
        requestQueue = Volley.newRequestQueue(this);


        request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request = new StringRequest(Request.Method.POST, URL2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("code") == "0") {
                                Toast.makeText(getApplicationContext(), "Failed to Update",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Successfully Updated",
                                        Toast.LENGTH_SHORT).show();
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
                        hashMap.put("email", id.getText().toString());
                        hashMap.put("name", name.getText().toString());
                        hashMap.put("birth", birth.getText().toString());
                        hashMap.put("phone", phone.getText().toString());

                        return hashMap;
                    }

                };
                requestQueue.add(request);
            }
        });
    }
    //-----------------------------------------------------//

/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri image = data.getData();
        try {
            bitmap = MediaStore.Images.Media.getBitmap(MyInfo.this.getContentResolver(), image);
            imBtn.setImageBitmap(bitmap);
            imBtn.setBackgroundColor(Color.rgb(0,0,0));
            imBtn.setPadding(0,0,0,0);
            //uploadImage();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {

                }
                return;
            }
        }
    }


}