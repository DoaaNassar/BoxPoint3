package com.example.duaa.boxpoint.Object;

import android.content.ClipData;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AL-Qema on 20/03/18.
 */

public class ShopsModel implements Serializable {

    private  boolean status;
    private String message;
    @SerializedName("items")
    private ItemObject items;

    public ShopsModel(boolean status, String message, ItemObject items) {
        this.status = status;
        this.message = message;
        this.items = items;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ItemObject getItems() {
        return items;
    }

    public void setItems(ItemObject items) {
        this.items = items;
    }
}
