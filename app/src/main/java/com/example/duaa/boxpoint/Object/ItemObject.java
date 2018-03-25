package com.example.duaa.boxpoint.Object;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AL-Qema on 20/03/18.
 */

public class ItemObject implements Serializable {
    private int id;
    private String title_en;
    private String title_ar;
    private String image;
    private String address_address;
    private String email;
    private int phone;
    private String about;
    private String type_of_services;
    private int user_id;
    private int created_by_id;
    private List<ProductObject> products;


    public ItemObject(int id, String title_en, String title_ar, String image, String address_address, String email, int phone, String about, String type_of_services, int user_id, int created_by_id, List<ProductObject> products) {
        this.id = id;
        this.title_en = title_en;
        this.title_ar = title_ar;
        this.image = image;
        this.address_address = address_address;
        this.email = email;
        this.phone = phone;
        this.about = about;
        this.type_of_services = type_of_services;
        this.user_id = user_id;
        this.created_by_id = created_by_id;
        this.products = products;
    }

    public ItemObject() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle_en() {
        return title_en;
    }

    public void setTitle_en(String title_en) {
        this.title_en = title_en;
    }

    public String getTitle_ar() {
        return title_ar;
    }

    public void setTitle_ar(String title_ar) {
        this.title_ar = title_ar;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress_address() {
        return address_address;
    }

    public void setAddress_address(String address_address) {
        this.address_address = address_address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getType_of_services() {
        return type_of_services;
    }

    public void setType_of_services(String type_of_services) {
        this.type_of_services = type_of_services;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCreated_by_id() {
        return created_by_id;
    }

    public void setCreated_by_id(int created_by_id) {
        this.created_by_id = created_by_id;
    }

    public List<ProductObject> getProducts() {
        return products;
    }

    public void setProducts(List<ProductObject> products) {
        this.products = products;
    }
}
