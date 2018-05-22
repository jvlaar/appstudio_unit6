package com.example.josvlaar.trivia;

public class Highscore {
    private int score;
    private String name;

    public Highscore(){
    }

    public Highscore(String aName, int aScore) {
        this.name = aName;
        this.score = aScore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
