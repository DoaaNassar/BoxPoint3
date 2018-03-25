package com.example.duaa.boxpoint.Object;

import java.io.Serializable;

/**
 * Created by AL-Qema on 19/03/18.
 */

public class ResponseUser implements Serializable {

    private boolean status;
    private String message;
    private User items;

    public ResponseUser() {
    }

    public ResponseUser(boolean status, String message, User items) {
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

    public User getItems() {
        return items;
    }

    public void setItems(User items) {
        this.items = items;
    }
}
