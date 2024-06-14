package com.example.studymate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText editTextID=findViewById(R.id.editTextID);
        EditText editTextPW=findViewById(R.id.editTextPW);
        Button buttonLogin=findViewById(R.id.buttonLogin);
        Button buttonSignUp=findViewById(R.id.buttonSignUp);

        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String id=editTextID.getText().toString();
                String pw=editTextPW.getText().toString();
                Signin signin=new Signin();
                boolean success=signin.loginUser(id,pw);

                if(success){
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("USER_ID",id);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this,"ID 또는 비밀번호가 맞지 않습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
