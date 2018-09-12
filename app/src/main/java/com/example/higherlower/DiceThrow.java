package com.example.higherlower;

public class DiceThrow {

    private String throwScore;

    public DiceThrow(String throwScore) {
        this.throwScore = throwScore;
    }

    public String getThrowScore() {
        return throwScore;
    }

    public void setThrowScore(String throwScore) {
        this.throwScore = throwScore;
    }

    @Override
    public String toString() {
        return throwScore;
    }
}
