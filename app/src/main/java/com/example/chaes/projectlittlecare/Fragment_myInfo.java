package com.example.chaes.projectlittlecare;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Fragment_myInfo extends Fragment implements View.OnClickListener {

    String user_email, user_name;
    private static final String URL = "http://52.79.162.197/user_info.php";
    private RequestQueue requestQueue;
    private StringRequest request;
    private TextView id, name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        user_email = getArguments().getString("email");
        View view = inflater.inflate(R.layout.fragment_my_info, container, false);

        id = (TextView)view.findViewById(R.id.myInfoId);
        name = (TextView)view.findViewById(R.id.myInfoName);
        id.setText(user_email);

        Button button = (Button)view.findViewById(R.id.myInfoModify);
        button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.myInfoModify:
                Intent intent = new Intent(getActivity(), MyInfo.class);
                intent.putExtra("email", user_email);
                getActivity().startActivity(intent);
        }
    }
}
