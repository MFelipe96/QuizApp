package com.example.appquiz;

public class Score {

    private int score = 0;

    public void setScore(int score) {
        this.score = score;
    }

    public Score() {

    }

    public int getScore() {
        return score;
    }

    public void addPontos() {
        score += 50;
    }

    public void subPontos(){
        if(score > 0)
            score -= 50;
        else
            score = 0;
    }
}
