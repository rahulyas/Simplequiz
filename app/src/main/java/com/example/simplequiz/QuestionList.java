package com.example.simplequiz;

import java.util.ArrayList;
import java.util.List;

public class QuestionList {

    private String question;
    private List<String> answers = new ArrayList<>();
    private String rightAnswer;

    public QuestionList(String question,String rightAnswer, String ... answers ) {
        this.question = question;
        this.rightAnswer = rightAnswer;
        this.answers.add(answers[0]);
        this.answers.add(answers[1]);
        this.answers.add(answers[2]);
        this.answers.add(answers[3]);
    }


    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }
}
