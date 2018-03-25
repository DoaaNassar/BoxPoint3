package com.example.duaa.boxpoint.Object;

import java.io.Serializable;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by AL-Qema on 18/03/18.
 */

public class UserModel  implements Serializable {

    String email ;
    String password ;
    String username ;
    String phone ;
    String first_name ;
    String last_name ;



    public UserModel(String email, String password, String username, String phone, String first_name, String last_name) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.phone = phone;
        this.first_name = first_name;
        this.last_name = last_name;
    }
    public UserModel(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }


}
