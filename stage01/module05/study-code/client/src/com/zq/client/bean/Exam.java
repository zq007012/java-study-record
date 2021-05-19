package com.zq.client.bean;

import java.io.Serializable;

/**
 * @ClassName: Exam
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/1/20 11:09
 * @Version: V1.0
 */
public class Exam implements Serializable {
    private static final long serialVersionUID = 8129829476200449175L;

    /**
     * UID, 是question的哈希码值question.hashcode()
     */
    private int uid;
    /**
     * 题目, 比如 1+1=?
     */
    private String question;
    /**
     * A选项的答案
     */
    private String aAnswer;
    /**
     * B选项的答案
     */
    private String bAnswer;
    /**
     * C选项的答案
     */
    private String cAnswer;
    /**
     * D选项的答案
     */
    private String dAnswer;
    /**
     * 正确选项, 只能是一个大写字母
     */
    private char correctOption;

    public Exam(int uid, String question, String aAnswer, String bAnswer,
                String cAnswer, String dAnswer, char correctOption) {
        this.uid = uid;
        this.question = question;
        this.aAnswer = aAnswer;
        this.bAnswer = bAnswer;
        this.cAnswer = cAnswer;
        this.dAnswer = dAnswer;
        setCorrectOption(correctOption);
    }

    public Exam() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public char getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(char correctOption) {
        this.correctOption = Character.toUpperCase(correctOption);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Exam exam = (Exam) o;

        if (uid != exam.uid) {
            return false;
        }
        return question.equals(exam.question);
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + question.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return question +
                System.lineSeparator() + "\t" + "A. " + aAnswer +
                System.lineSeparator() + "\t" + "B. " + bAnswer +
                System.lineSeparator() + "\t" + "C. " + cAnswer +
                System.lineSeparator() + "\t" + "D. " + dAnswer;
    }

    public String getOption(char optionLetter) {
        String option;
        switch (optionLetter) {
            case 'A':
                option = "A. " + aAnswer;
                break;
            case 'B':
                option = "B. " + bAnswer;
                break;
            case 'C':
                option = "C. " + cAnswer;
                break;
            case 'D':
                option = "D. " + dAnswer;
                break;
            default:
                option = null;
                break;
        }

        return option;
    }

    public String toStringWithCorrectOption() {
        return toString() + System.lineSeparator() + "正确答案: " + getOption(correctOption);
    }

    public String getaAnswer() {
        return aAnswer;
    }

    public void setaAnswer(String aAnswer) {
        this.aAnswer = aAnswer;
    }

    public String getbAnswer() {
        return bAnswer;
    }

    public void setbAnswer(String bAnswer) {
        this.bAnswer = bAnswer;
    }

    public String getcAnswer() {
        return cAnswer;
    }

    public void setcAnswer(String cAnswer) {
        this.cAnswer = cAnswer;
    }

    public String getdAnswer() {
        return dAnswer;
    }

    public void setdAnswer(String dAnswer) {
        this.dAnswer = dAnswer;
    }
}
