package com.sq.shop;

import java.security.PrivateKey;

public class ShoppingDao {
    private int shop_id ;
    private String shop_name;
    private double shop_price;
    private String shop_img;

    public ShoppingDao(int shop_id, String shop_name, double shop_price, String shop_img) {
        this.shop_id = shop_id;
        this.shop_name = shop_name;
        this.shop_price = shop_price;
        this.shop_img = shop_img;
    }

    public ShoppingDao() {
    }

    @Override
    public String toString() {
        return "ShoppingDao{" +
                "shop_id=" + shop_id +
                ", shop_name='" + shop_name + '\'' +
                ", shop_price=" + shop_price +
                ", shop_img='" + shop_img + '\'' +
                '}';
    }
}
