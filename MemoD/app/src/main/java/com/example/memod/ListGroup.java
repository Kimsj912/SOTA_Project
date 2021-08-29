package com.example.memod;

public class ListGroup extends ListGeneral{

    // Attribute
    private String groupname;
    private String groupIntro;
    private String groupImgUrl;

    // getter
    public String getGroupname() {return getName();}
    public String getGroupIntro() {return getInfo();}
    public String getGroupImgUrl() {return getUrl(); }

    // Constructor
    public ListGroup(String name, String info, String url){
        super(name,info,url);
        this.groupname = name;
        this.groupIntro = info;
        this.groupImgUrl = url;
    }

}