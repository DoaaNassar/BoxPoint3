package com.example.duaa.boxpoint.Object;

/**
 * Created by AL-Qema on 15/03/18.
 */

public class MenuObject {
    String name ;
    int image ;

    public MenuObject(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
