package com.sq.shop;

public class MyshopDao {
    private int shop_id ;
    private String shop_name;
    private double shop_price;
    private String shop_img;
    private int buynumber;
    private String address;
    private String phonenumber;

    public MyshopDao(int shop_id, String shop_name, double shop_price, String shop_img, int buynumber, String address, String phonenumber) {
        this.shop_id = shop_id;
        this.shop_name = shop_name;
        this.shop_price = shop_price;
        this.shop_img = shop_img;
        this.buynumber = buynumber;
        this.address = address;
        this.phonenumber = phonenumber;
    }

    @Override
    public String toString() {
        return "MyshopDao{" +
                "shop_id=" + shop_id +
                ", shop_name='" + shop_name + '\'' +
                ", shop_price=" + shop_price +
                ", shop_img='" + shop_img + '\'' +
                ", buynumber=" + buynumber +
                ", address='" + address + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                '}';
    }
}
