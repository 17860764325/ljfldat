package com.sq.dtlist;

public class DtListDao {
    private String questions;
    private String option_A;
    private String option_B;
    private String option_C;
    private String option_D;
    private String question_true;
    private int question_type;

    public DtListDao(String questions, String option_A, String option_B, String option_C, String option_D, String question_true, int question_type) {
        this.questions = questions;
        this.option_A = option_A;
        this.option_B = option_B;
        this.option_C = option_C;
        this.option_D = option_D;
        this.question_true = question_true;
        this.question_type = question_type;
    }

    @Override
    public String toString() {
        return "DtListDao{" +
                "questions='" + questions + '\'' +
                ", option_A='" + option_A + '\'' +
                ", option_B='" + option_B + '\'' +
                ", option_C='" + option_C + '\'' +
                ", option_D='" + option_D + '\'' +
                ", question_true='" + question_true + '\'' +
                ", question_type=" + question_type +
                '}';
    }
}
