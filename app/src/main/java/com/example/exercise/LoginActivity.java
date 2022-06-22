package com.example.exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    EditText idEditText;
    EditText pwEditText;

    String userId;
    String userPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton=(Button) findViewById(R.id.loginButton);
        idEditText=(EditText) findViewById(R.id.idEditText);
        pwEditText=(EditText) findViewById(R.id.pwEditText);

        userId="1";
        userPw="111111";



        loginButton.setOnClickListener(new View.OnClickListener() {//로그인 버튼 클릭시 (성공했다고 가정)
            @Override
            public void onClick(View v) {
                if((userId.equals(idEditText.getText().toString()))&&(userPw.equals(pwEditText.getText().toString()))) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    SharedPreferences info=getSharedPreferences("Info", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor infoEdit=info.edit();
                    infoEdit.putString("userID",userId);
                    infoEdit.commit();

                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Login Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}