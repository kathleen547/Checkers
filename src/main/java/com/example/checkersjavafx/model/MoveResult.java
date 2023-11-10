package com.example.checkersjavafx.model;

import javafx.util.Pair;

public class MoveResult {
    private boolean correct = false, promoted = false;
    Pair<Integer, Integer> killedPos = null;

    public MoveResult(boolean correct) {
        this.correct = correct;
    }
    public void setCorrect(boolean correct){
        this.correct = correct;
    }
    public void setPromoted(boolean promoted){
        this.promoted = promoted;
    }
    public void setKilledPos(int x, int y){
        this.killedPos = new Pair<>(x, y);
    }
    public boolean isCorrect() {
        return correct;
    }
    public boolean isPromoted() {
        return promoted;
    }

    public Pair<Integer, Integer> getKilledPos() {
        return killedPos;
    }
}