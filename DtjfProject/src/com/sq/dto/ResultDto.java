package com.sq.dto;

public class ResultDto {
    private boolean result;
    private String msg;

    public ResultDto(boolean result, String  msg) {
        this.result = result;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResultDto{" +
                "result=" + result +
                ", msg=" + msg +
                '}';
    }
}
