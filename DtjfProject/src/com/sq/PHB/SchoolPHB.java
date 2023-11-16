package com.sq.PHB;


import java.util.Arrays;

public class SchoolPHB implements PHB{
    private String  Name ;
    private int number;

    @Override
    public void sucess() {
        System.out.println("这是学校排行榜");
    }


    public SchoolPHB(String schoolName, int number) {
        Name = schoolName;
        this.number = number;
    }


    @Override
    public String toString() {
        return "SchoolPHB{" +
                "Name='" + Name + '\'' +
                ", number=" + number +
                '}';
    }
}
