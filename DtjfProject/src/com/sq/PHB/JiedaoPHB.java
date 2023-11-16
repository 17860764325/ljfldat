package com.sq.PHB;

public class JiedaoPHB implements PHB {

    private String Name;
    private int number;

    @Override
    public void sucess() {
        System.out.println("街道排行榜");
    }

    public JiedaoPHB(String name, int number) {
        Name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return "JiedaoPHB{" +
                "Name='" + Name + '\'' +
                ", number=" + number +
                '}';
    }
}
