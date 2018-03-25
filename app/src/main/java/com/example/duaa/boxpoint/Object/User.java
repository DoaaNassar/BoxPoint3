package com.example.duaa.boxpoint.Object;


import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by AL-Qema on 19/03/18.
 */

public class User extends RealmObject implements Serializable{
    private String id;
    private String email;
    private String password;
    private String username;
    private String phone;
    private String first_name;
    private String last_name;

    private String token_type;
    private String expires_in;
    private String access_token;
    private String refresh_token;

    public User(String id, String email, String password, String username, String phone, String first_name,
                String last_name, String token_type, String expires_in, String access_token, String refresh_token) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.phone = phone;
        this.first_name = first_name;
        this.last_name = last_name;
        this.token_type = token_type;
        this.expires_in = expires_in;
        this.access_token = access_token;
        this.refresh_token = refresh_token;
    }

    public User() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}
