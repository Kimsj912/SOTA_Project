package com.example.memod;

public class ListUser extends ListGeneral{

    // Attribute
    private String username;
    private String userInfo;
    private String userProfileUrl;

    // getter
    public String getUsername() {return getName();}
    public String getUserInfo() {return getInfo();}
    public String getUserProfileUrl() {return getUrl(); }

    // Constructor
    public ListUser(String name, String info, String url){
        super(name,info,url);
        this.username = name;
        this.userInfo = info;
        this.userProfileUrl = url;
    }

}