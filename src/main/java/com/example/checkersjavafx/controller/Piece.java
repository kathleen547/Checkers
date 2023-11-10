package com.example.checkersjavafx.controller;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Piece extends Circle {

    private static final Color whiteRegularColor = Color.LIGHTGREEN;
    private static final Color whiteKingColor = Color.DARKGREEN;
    private static final Color blackRegularColor = Color.LIGHTBLUE;
    private static final Color blackKingColor = Color.DARKBLUE;

    int x, y;
    public boolean isWhite;
    Piece(double radius){
        super(radius);
    }

    Piece(double radius, int x, int y, boolean isWhite) {
        super(radius, blackRegularColor);
        if (isWhite)
            setFill(whiteRegularColor);
        this.isWhite = isWhite;
        this.x = x;
        this.y = y;
    }

    public void setPos(int a, int b) {
        x = a;
        y = b;
    }

    public void promote() {
        if(isWhite) setFill(whiteKingColor);
        else setFill(blackKingColor);
    }
}
