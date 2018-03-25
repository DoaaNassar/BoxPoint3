package com.example.duaa.boxpoint.Object;

import java.io.Serializable;

/**
 * Created by AL-Qema on 13/03/18.
 */

public class CategoryBuyModel implements Serializable {
    String name ;
    String number;
    String price;

    public CategoryBuyModel(String name, String number, String price) {
        this.name = name;
        this.number = number;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
