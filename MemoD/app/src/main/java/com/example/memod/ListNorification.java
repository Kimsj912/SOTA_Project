package com.example.memod;

public class ListNorification extends ListGeneral{

    // Variable
    private String image;
    private String txtName;
    private String image2;


    // constructor
    public ListNorification(String name, String info, String url){
        super(name, info, url);
        this.image = name;
        this.txtName = info;
        this.image2 = url;

    }

    // get & set
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getTxtName() {
        return txtName;
    }
    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }
    public String getImage2() {
        return image2;
    }
    public void setImage2(String image2) {this.image2 = image2;}

}
