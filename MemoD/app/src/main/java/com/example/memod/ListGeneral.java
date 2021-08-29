package com.example.memod;

import java.util.ArrayList;

public class ListGeneral {

    // Attribute
    private String name;
    private String info;
    private String url;

    // Getter
    public String getName() {
        return name;
    }
    public String getInfo() {
        return info;
    }
    public String getUrl() {
        return url;
    }

    // Constructor
    public ListGeneral(String name, String info, String url){
        this.name = name;
        this.info = info;
        this.url = url;
    }

}
