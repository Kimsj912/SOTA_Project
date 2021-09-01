package com.example.memod;

public class ListTimeLineRow extends ListGeneral{

    // Attribute
    private String date;
    private String place;

    // constructor
    public ListTimeLineRow(String name, String info, String url) {
        super(name, info, url);
        this.date = name;
        this.place = info;
    }

    // get & set
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
}
