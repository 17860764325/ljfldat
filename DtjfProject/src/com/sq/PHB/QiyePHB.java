package com.sq.PHB;

public class QiyePHB implements  PHB {

    private String Name;
    private int number;

    @Override
    public void sucess() {

    }

    public QiyePHB(String name, int number) {
        Name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return "QiyePHB{" +
                "Name='" + Name + '\'' +
                ", number=" + number +
                '}';
    }
}
