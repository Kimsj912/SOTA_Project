package com.example.memod;

public class ListHomeGroup extends  ListGeneral{

    // Attribute
    private String postTitle;
    private String postImgUrl;

    // Getter
    public String getPostTitle() {return getName();}
    public String getPostImgUrl() {return getUrl(); }

    // Constructor
    public ListHomeGroup(String name, String info, String url){
        super(name,
                info,
                url);
        this.postTitle = name;
        this.postImgUrl = url;
    }

}
