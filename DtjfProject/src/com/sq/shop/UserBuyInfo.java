package com.sq.shop;

public class UserBuyInfo {
    private  String name;
    private int number;

    public UserBuyInfo() {
    }

    public UserBuyInfo(String name, int number ) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return "UserBuyInfo{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
