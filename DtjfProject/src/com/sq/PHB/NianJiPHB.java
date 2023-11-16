package com.sq.PHB;

import java.util.Arrays;

public class NianJiPHB implements PHB {
    private String Name;
    private int number ;

    @Override
    public void sucess() {
        System.out.println("这是年级排名");
    }

    public NianJiPHB() {
    }

    public NianJiPHB(String njName, int number) {
        Name = njName;
        this.number = number;
    }

    @Override
    public String toString() {
        return "NianJiPHB{" +
                "Name='" + Name + '\'' +
                ", number=" + number +
                '}';
    }
}
