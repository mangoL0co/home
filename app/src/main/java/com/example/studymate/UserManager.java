package com.example.studymate;

import java.util.ArrayList;
import java.util.List;

public class UserManager{
    private List<User> userList;
    private static UserManager instance;

    private UserManager(){
        userList=new ArrayList<>();
    }
    public static UserManager getInstance(){
        if(instance==null){
            instance=new UserManager();
        }
        return instance;
    }
    public void addUser(User user){
        userList.add(user);
    }

    public boolean validateUser(String userId,String password){
        for(User user : userList){
            if(user.getUserId().equals(userId) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    public User getUser(String userId){
        for(User user : userList) {
            if(user.getUserId().equals(userId)){
                return user;
            }
        }
        return null;
    }
}
