package com.sq.PHB;

public class QypeoplePHB implements  PHB {
    private String Name;
    private int number;


    @Override
    public void sucess() {

    }

    public QypeoplePHB(String name, int number) {
        Name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return "QypeoplePHB{" +
                "Name='" + Name + '\'' +
                ", number=" + number +
                '}';
    }
}
