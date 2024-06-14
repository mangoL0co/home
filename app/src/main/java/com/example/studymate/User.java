package com.example.studymate;

public class User{
    private String userId;
    private String password;
    private String name;
    private String university;
    private String major;
    public User(String userId, String password, String name, String university, String major){
        this.userId= userId;
        this.password=password;
        this.name= name;
        this.university=university;
        this.major=major;
    }

    public String getUserId(){
        return userId;
    }
    public String getPassword(){
        return password;
    }
    public String getName(){
        return name;
    }

    public String getUniversity(){
        return university;
    }
    public String getMajor(){
        return major;
    }
    public void updateProfile(String name,String university, String major) {
        this.name= name;
        this.university=university;
        this.major=major;
    }
}
