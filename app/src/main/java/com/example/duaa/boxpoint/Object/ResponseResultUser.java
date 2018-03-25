package com.example.duaa.boxpoint.Object;

import java.io.Serializable;

/**
 * Created by AL-Qema on 18/03/18.
 */

public class ResponseResultUser implements Serializable {
boolean status ;

    public ResponseResultUser(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

