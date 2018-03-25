package com.example.duaa.boxpoint.Object;

import java.io.Serializable;

/**
 * Created by AL-Qema on 11/03/18.
 */

public class PackageModel implements Serializable {

    String name;
    String image;
    String numberPoint;
    String description;

    public PackageModel(String name, String image, String numberPoint, String description) {
        this.name = name;
        this.image = image;
        this.numberPoint = numberPoint;
        this.description = description;
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

    public String getNumberPoint() {
        return numberPoint;
    }

    public void setNumberPoint(String numberPoint) {
        this.numberPoint = numberPoint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
