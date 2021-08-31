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
    public String profilePhotoUrl ;
    public String infomation ;
    public String year ;
    public String month ;
    public String day ;
    public String sex ;
    public String location1 ;
    public String location2 ;

    public String pushToken;

    public void createUser(String email, String password, String name, String phone){
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public void setAddition(String profilePhotoUrl, String infomation, String year, String month, String day, String sex, String location1, String location2){
        this.profilePhotoUrl = profilePhotoUrl;
        this.infomation = infomation;
        this.year = year;
        this.month = month;
        this.day = day;
        this.sex = sex;
        this.location1 = location1;
        this.location2 = location2;
    }
    public void setAddition(String infomation, String year, String month, String day, String sex, String location1, String location2){
        this.infomation = infomation;
        this.year = year;
        this.month = month;
        this.day = day;
        this.sex = sex;
        this.location1 = location1;
        this.location2 = location2;
    }

    // get & set

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public String getUid() {
        return uid;
    }
    public String getPhone() {
        return phone;
    }
    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }
    public String getInfomation() {
        return infomation;
    }
    public String getYear() {
        return year;
    }
    public String getMonth() {
        return month;
    }
    public String getDay() {
        return day;
    }
    public String getSex() {
        return sex;
    }
    public String getLocation2() {
        return location2;
    }
    public String getLocation1() {
        return location1;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }
    public void setInfomation(String infomation) {
        this.infomation = infomation;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public void setLocation1(String location1) {
        this.location1 = location1;
    }
    public void setLocation2(String location2) {
        this.location2 = location2;
    }



}
