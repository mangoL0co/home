package com.example.studymate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EditText editTextID=findViewById(R.id.editTextID);
        EditText editTextPW= findViewById(R.id.editTextPW);
        EditText editTextPWConfirm=findViewById(R.id.editTextPWConfirm);
        EditText editTextUniversity=findViewById(R.id.editTextUniversity);
        EditText editTextDepartment=findViewById(R.id.editTextDepartment);
        Button buttonSignUp=findViewById(R.id.buttonSignUp);

        buttonSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String id= editTextID.getText().toString();
                String pw=editTextPW.getText().toString();
                String pwConfirm=editTextPWConfirm.getText().toString();
                String university=editTextUniversity.getText().toString();
                String department=editTextDepartment.getText().toString();

                if(!pw.equals(pwConfirm)){
                    Toast.makeText(SignUpActivity.this,"비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show();
                    return;
                }
                User user=new User(id,pw,"",university,department);
                UserManager.getInstance().addUser(user);
                Toast.makeText(SignUpActivity.this,"회원가입이 완료되었습니다.",Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
