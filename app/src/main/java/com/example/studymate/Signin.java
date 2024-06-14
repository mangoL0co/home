package com.example.studymate;

public class Signin{
    public boolean loginUser(String userId,String password){
        return UserManager.getInstance().validateUser(userId,password);
    }
}
