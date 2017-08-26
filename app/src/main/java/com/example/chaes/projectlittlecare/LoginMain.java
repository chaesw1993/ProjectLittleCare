package com.example.chaes.projectlittlecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.chaes.projectlittlecare.LCClass.FindInfo;
import com.example.chaes.projectlittlecare.LCClass.SignUp;
import com.example.chaes.projectlittlecare.LCClass.UnPFlag;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginMain extends AppCompatActivity {
    UnPFlag user = new UnPFlag();
    private static final String URL = "http://52.79.162.197/user_control.php";
    private RequestQueue requestQueue;
    private StringRequest request;
    private EditText email, password;
    private Button btn;
    String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference myRef;
    FirebaseUser fbUser;
    ProgressBar pbLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);


        btn = (Button) findViewById(R.id.btn_login);
        TextView textView = (TextView) findViewById(R.id.link_signup);
        TextView textView2 = (TextView) findViewById(R.id.link_find);
        email = (EditText)findViewById(R.id.input_email);
        password = (EditText)findViewById(R.id.input_password);
        pbLogin = (ProgressBar)findViewById(R.id.pbLogin);

        requestQueue = Volley.newRequestQueue(this);

        btn.setOnClickListener(new View.OnClickListener() { // 제공자
            @Override
            public void onClick(View v) {
                user.setUnPFlag(2);
                Intent intent = new Intent(LoginMain.this, MenuMain.class);
                intent.putExtra("UnPFlag", user);
                intent.putExtra("email", email.getText().toString());
                startActivity(intent);
                //finish();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() { // 유저
            @Override
            public void onClick(View v) {

                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.names().get(0).equals("success")) {
                                Toast.makeText(getApplicationContext(), "Success " + jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
                                user.setUnPFlag(1);
                                Intent intent = new Intent(LoginMain.this, MenuMain.class);
                                intent.putExtra("UnPFlag", user);
                                intent.putExtra("email", email.getText().toString());
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Error "+jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
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

                        return hashMap;
                    }
                };

                requestQueue.add(request);

                if (email.getText().toString().isEmpty() ||email.getText().toString().equals("") ||password.getText().toString().isEmpty() || password.getText().toString().equals("") ){
                    Toast.makeText(LoginMain.this, "잘못입력하셨습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    userLogin(email.getText().toString(), password.getText().toString());
                }

            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginMain.this, SignUp.class);
                startActivity(intent);
                //finish();

            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginMain.this, FindInfo.class);
                startActivity(intent);
                //finish();

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
    private void userLogin(String email, String password){
        pbLogin.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        pbLogin.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail", task.getException());
                            Toast.makeText(LoginMain.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {

                        }
                    }
                });
    }
}
