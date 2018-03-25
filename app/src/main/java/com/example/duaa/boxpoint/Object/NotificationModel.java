package com.example.duaa.boxpoint.Object;

import java.io.Serializable;

/**
 * Created by AL-Qema on 13/03/18.
 */

public class NotificationModel implements Serializable {

    String name ;
    String image ;
    String hour;

    public NotificationModel(String name, String image, String hour) {
        this.name = name;
        this.image = image;
        this.hour = hour;
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

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
