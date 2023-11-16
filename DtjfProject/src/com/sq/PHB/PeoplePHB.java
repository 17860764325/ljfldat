package com.sq.PHB;

public class PeoplePHB implements PHB {

    private String Name;
    private int number;

    @Override
    public void sucess() {
        System.out.println("这是个人排名");
    }

    public PeoplePHB() {
    }

    public PeoplePHB(String peopleName, int number) {
        Name = peopleName;
        this.number = number;
    }

    @Override
    public String toString() {
        return "PeoplePHB{" +
                "PeopleName='" + Name + '\'' +
                ", number=" + number +
                '}';
    }
}
