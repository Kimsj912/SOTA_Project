package com.example.memod;

public class ListPost extends  ListGeneral{

    // Attribute
    private String postTitle;
    private String postContext;
    private String postImgUrl;

    // Getter
    public String getPostTitle() {return getName();}
    public String getPostContext() {return getInfo();}
    public String getPostImgUrl() {return getUrl(); }

    // Constructor
    public ListPost(String name, String info, String url){
        super(name,info,url);
        this.postTitle = name;
        this.postContext = info;
        this.postImgUrl = url;
    }

}
