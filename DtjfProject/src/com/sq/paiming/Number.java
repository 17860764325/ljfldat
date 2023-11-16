package com.sq.paiming;

public class Number {
    private  int number;
    private int answer;
    private int lastnumebr;

    public Number(int number, int answer,int lastnumebr) {
        this.number = number;
        this.answer = answer;
        this.lastnumebr = lastnumebr;
    }

    @Override
    public String toString() {
        return "Number{" +
                "number=" + number +
                ", answer=" + answer +
                ", lastnumebr=" + lastnumebr +
                '}';
    }
}
