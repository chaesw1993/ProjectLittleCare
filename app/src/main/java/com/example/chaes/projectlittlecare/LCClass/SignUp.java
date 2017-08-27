package com.example.chaes.projectlittlecare.LCClass;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    private static final String URL = "http://52.79.162.197/insertUser.php";
    private RequestQueue requestQueue;
    private StringRequest request;
    private EditText email, password, birth, name, phone;
    private Button button;

    String TAG = "SignUp";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseUser fbuser;
    String stEmail;
    String stPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                fbuser = firebaseAuth.getCurrentUser();

                if (fbuser != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + fbuser.getUid());

                    SharedPreferences sharedPreferences = getSharedPreferences("email", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("uid", fbuser.getUid());
                    editor.putString("email", fbuser.getEmail());
                    editor.apply();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        button = (Button) findViewById(R.id.button);
        email = (EditText) findViewById(R.id.sign_id);
        password = (EditText) findViewById(R.id.sign_pw);
        name = (EditText) findViewById(R.id.sign_name);
        birth = (EditText) findViewById(R.id.sign_birth);
        phone = (EditText) findViewById(R.id.sign_phone);
        requestQueue = Volley.newRequestQueue(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stEmail = email.getText().toString();
                stPassword = password.getText().toString();

                if (stEmail.isEmpty() || stEmail.equals("") || stPassword.isEmpty() || stPassword.equals("")) {
                    Toast.makeText(SignUp.this, "모두채워주세요", Toast.LENGTH_SHORT).show();
                } else {
                    regiserUser(stEmail, stPassword);
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
                            hashMap.put("role", "user");

                            return hashMap;
                        }
                    };
                    requestQueue.add(request);
                }
            }
        });
    }




    public void regiserUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Toast.makeText(SignUp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {


                            Toast.makeText(SignUp.this, "Authentication success.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }




    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}




