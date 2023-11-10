package com.example.checkersjavafx.controller;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends Rectangle {

    public Square(int x, int y, int size, boolean isWhite){
        setWidth(size);
        setHeight(size);
        relocate(x * size, y * size);
        setFill(isWhite ? Color.WHITE : Color.BLACK);
    }
}
