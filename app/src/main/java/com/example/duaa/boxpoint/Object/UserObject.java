package com.example.duaa.boxpoint.Object;

/**
 * Created by AL-Qema on 19/03/18.
 */

public class UserObject {
    Boolean status;
    UserDataResponse items;

    public UserObject(Boolean status, UserDataResponse userDataResponse) {
        this.status = status;
        this.items = userDataResponse;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "status=" + status +
                ", userDataResponse=" + items +
                '}';
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public UserDataResponse getUserDataResponse() {
        return items;
    }

    public void setUserDataResponse(UserDataResponse userDataResponse) {
        this.items = userDataResponse;
    }
}


