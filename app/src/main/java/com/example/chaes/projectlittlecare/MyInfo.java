package com.example.chaes.projectlittlecare;

import android.*;
import android.Manifest;
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
import android.widget.ImageView;
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
import com.google.firebase.auth.FirebaseAuth;
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

    private StorageReference mStorageRef;
    private ImageButton ivUser;
    String TAG = getClass().getSimpleName();

    Bitmap bitmap;
    String stUid;
    String stEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        ivUser = (ImageButton) findViewById(R.id.user_profile_photo);



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


        //--------------------------
        mStorageRef = FirebaseStorage.getInstance().getReference();

        SharedPreferences sharedPreferences = MyInfo.this.getSharedPreferences("email", Context.MODE_PRIVATE);
        stUid = sharedPreferences.getString("uid", "");
        stEmail = sharedPreferences.getString("email", "");


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef.child("users").child(stUid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue().toString();
                String stPhoto = dataSnapshot.child("photo").getValue().toString();

                if (TextUtils.isEmpty(stPhoto)) {


                } else {
                    ivUser.setBackgroundColor(Color.rgb(0,0,0));
                    ivUser.setPadding(0,0,0,0);
                    Picasso.with(MyInfo.this)
                            .load(stPhoto)
                            .fit()
                            .centerInside()
                            .into(ivUser, new Callback.EmptyCallback() {
                                @Override
                                public void onSuccess() {
                                    // Index 0 is the image view.
                                    Log.d(TAG, "SUCCESS");

                                }
                            });
                }


                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        if (ContextCompat.checkSelfPermission(MyInfo.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MyInfo.this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(MyInfo.this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }


        ivUser = (ImageButton) findViewById(R.id.user_profile_photo);
        ivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 1);

            }
        });
    }

    public void uploadImage() {

        StorageReference mountainsRef = mStorageRef.child("users").child(stUid + ".jpg");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @SuppressWarnings("VisibleForTests")
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                String photoUri = String.valueOf(downloadUrl);
                Log.d("url", photoUri);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("users");

                Hashtable<String, String> profile = new Hashtable<String, String>();
                profile.put("email", stEmail);
                profile.put("key", stUid);
                profile.put("photo", photoUri);

                myRef.child(stUid).setValue(profile);
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String s = dataSnapshot.getValue().toString();
                        Log.d("Profile", s);
                        if (dataSnapshot != null) {
                            Toast.makeText(MyInfo.this, "사진 업로드가 되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri image = data.getData();
        try {
            bitmap = MediaStore.Images.Media.getBitmap(MyInfo.this.getContentResolver(), image);
            ivUser.setImageBitmap(bitmap);
            ivUser.setBackgroundColor(Color.rgb(0,0,0));
            ivUser.setPadding(0,0,0,0);
            uploadImage();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}


