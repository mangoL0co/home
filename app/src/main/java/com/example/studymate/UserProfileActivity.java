package com.example.studymate;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UserProfileActivity extends AppCompatActivity{
    private EditText editTextID,editTextPW,editTextPWConfirm,editTextUniversity,editTextDepartment;
    private String userId;
    private User userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        editTextID=findViewById(R.id.editTextID);
        editTextPW=findViewById(R.id.editTextPW);
        editTextPWConfirm=findViewById(R.id.editTextPWConfirm);
        editTextUniversity=findViewById(R.id.editTextUniversity);
        editTextDepartment= findViewById(R.id.editTextDepartment);
        Button buttonSave= findViewById(R.id.buttonSave);
        userId=getIntent().getStringExtra("USER_ID");
        userInfo=UserManager.getInstance().getUser(userId);

        editTextID.setText(userInfo.getUserId());
        editTextPW.setText(userInfo.getPassword());
        editTextUniversity.setText(userInfo.getUniversity());
        editTextDepartment.setText(userInfo.getMajor());

        buttonSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showConfirmationDialog();
            }
        });
    }
    private void showConfirmationDialog(){
        new AlertDialog.Builder(this)
                .setTitle("저장하시겠습니까?")
                .setPositiveButton("네",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        saveUserInfo();
                    }
                })
                .setNegativeButton("아니요",null)
                .show();
    }

    private void saveUserInfo(){
        String pw=editTextPW.getText().toString();
        String pwConfirm=editTextPWConfirm.getText().toString();
        String university=editTextUniversity.getText().toString();
        String department=editTextDepartment.getText().toString();

        if (!pw.equals(pwConfirm)){
            Toast.makeText(UserProfileActivity.this,"비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show();
            return;
        }
        userInfo.updateProfile(userInfo.getName(),university,department);
        Toast.makeText(UserProfileActivity.this,"정보가 저장되었습니다.",Toast.LENGTH_SHORT).show();
        finish();
    }
}
