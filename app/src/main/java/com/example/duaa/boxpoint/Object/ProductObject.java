package com.example.duaa.boxpoint.Object;

import java.io.Serializable;

/**
 * Created by AL-Qema on 20/03/18.
 */

public class ProductObject implements Serializable {

    int id;
    String title_en;
    String title_ar ;
    String description_en;
    String description_ar;
    String price;
    int discount;
    String is_featured;
    int discount_code ;
    int price_by_points ;
//    int view_count ;
//    int sale_count ;
    int shop_id ;

    public ProductObject(int id, String title_en, String title_ar, String description_en, String description_ar, String price, int discount, String is_featured, int discount_code, int price_by_points, int shop_id) {
        this.id = id;
        this.title_en = title_en;
        this.title_ar = title_ar;
        this.description_en = description_en;
        this.description_ar = description_ar;
        this.price = price;
        this.discount = discount;
        this.is_featured = is_featured;
        this.discount_code = discount_code;
        this.price_by_points = price_by_points;
        this.shop_id = shop_id;
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

    public String getDescription_en() {
        return description_en;
    }

    public void setDescription_en(String description_en) {
        this.description_en = description_en;
    }

    public String getDescription_ar() {
        return description_ar;
    }

    public void setDescription_ar(String description_ar) {
        this.description_ar = description_ar;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getIs_featured() {
        return is_featured;
    }

    public void setIs_featured(String is_featured) {
        this.is_featured = is_featured;
    }

    public int getDiscount_code() {
        return discount_code;
    }

    public void setDiscount_code(int discount_code) {
        this.discount_code = discount_code;
    }

    public int getPrice_by_points() {
        return price_by_points;
    }

    public void setPrice_by_points(int price_by_points) {
        this.price_by_points = price_by_points;
    }


    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }
}
