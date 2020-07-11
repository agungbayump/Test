package com.agungbayump.maintenanceassistance.model;

public class TestFaultModel {

    private String question;
    private int ifQuestion, questionNumber;
    private Boolean condition;

    public TestFaultModel() {

    }

    public TestFaultModel(String question, int ifQuestion, int questionNumber, Boolean condition) {
        this.question = question;
        this.ifQuestion = ifQuestion;
        this.questionNumber = questionNumber;
        this.condition = condition;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getIfQuestion() {
        return ifQuestion;
    }

    public void setIfQuestion(int ifQuestion) {
        this.ifQuestion = ifQuestion;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public Boolean getCondition() {
        return condition;
    }

    public void setCondition(Boolean condition) {
        this.condition = condition;
    }
}
