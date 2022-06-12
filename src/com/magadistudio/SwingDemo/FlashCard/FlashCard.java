package com.magadistudio.SwingDemo.FlashCard;

public class FlashCard {
    private  String questions;
    private  String answer;

    public FlashCard(String q, String a)
    {
        this.questions = q;
       this. answer = a;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
