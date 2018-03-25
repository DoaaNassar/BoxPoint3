package com.example.duaa.boxpoint.Object;

/**
 * Created by AL-Qema on 11/03/18.
 */

public class CategoryModel {

    String name;
    String image ;
    String point ;
    String price ;

    public CategoryModel(String name, String image, String point, String price) {
        this.name = name;
        this.image = image;
        this.point = point;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
