package com.example.duaa.boxpoint.Object;

import java.io.Serializable;

/**
 * Created by AL-Qema on 13/03/18.
 */

public class OfferModel implements Serializable  {
String image ;
String name ;
String description ;
int check ;

    public OfferModel(String image, String name, String description ,int check ) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.check = check ;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }
}
