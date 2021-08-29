package com.example.memod;

import android.net.Uri;

import com.google.android.gms.tasks.Task;

public class UserModel {
    // important Attribute
    public String email;
    public String password;
    public String name;
    public String uid; // 현재 사용자(로그인한)
    public String phone;

    // additional Attribute
    public Task<Uri> profilePhotoUrl ;
    public String infomation ;
    public String year ;
    public String month ;
    public String day ;
    public String sex ;
    public String location1 ;
    public String location2 ;

    public String pushToken;

    public void createUser(String uid, String email, String password, String name, String phone){
        this.uid = uid;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public void setAddition(Task<Uri> profilePhotoUrl, String infomation, String year, String month, String day, String sex, String location1, String location2){
        this.profilePhotoUrl = profilePhotoUrl;
        this.infomation = infomation;
        this.year = year;
        this.month = month;
        this.day = day;
        this.sex = sex;
        this.location1 = location1;
        this.location2 = location2;
    }

}
