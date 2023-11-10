package com.example.checkersjavafx.controller;

import com.example.checkersjavafx.model.Board;
import com.example.checkersjavafx.model.Game;
import com.example.checkersjavafx.model.MoveResult;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.ArrayList;

public class BoardController {

    public static final int GRID_SIZE = 8;
    public static final int CELL_SIZE = 80;
    public static final int PIECE_RADIUS = CELL_SIZE / 2 - 5;

    private Integer sourceX = null;
    private Integer sourceY = null;

    Game game;
    Group root;
    ArrayList<Piece> pieces = new ArrayList<>();

    public BoardController(Game game) {
        this.game = game;
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #d2b48c;");
        boolean isWhite = false;
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                isWhite = (row + col) % 2 == 1;
                Square cell = new Square(row, col, CELL_SIZE, isWhite);
                gridPane.add(cell, col, row);
            }
        }
        root = new Group(gridPane);
        Board.Piece[][] initialPieces = game.getPieces();
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                switch (initialPieces[row][col]) {
                    case WhiteRegular -> {
                        Piece piece = createPiece(row, col, true);
                        pieces.add(piece);
                        root.getChildren().add(piece);
                    }
                    case BlackRegular -> {
                        Piece piece = createPiece(row, col, false);
                        pieces.add(piece);
                        root.getChildren().add(piece);
                    }
                }
            }
        }
    }

    public Group getRoot() {
        return root;
    }

    public Piece createPiece(int x, int y, boolean isWhite) {
        // Create piece
        Piece piece = new Piece(PIECE_RADIUS, x, y, isWhite);
        Pair<Double, Double> pos = posFromCoord(x, y);
        piece.setCenterX(pos.getKey());
        piece.setCenterY(pos.getValue());

        // Program piece
        piece.setOnMousePressed(event -> {
            Pair<Integer, Integer> coords = coordFromPos(event.getSceneX(), event.getSceneY());
            sourceX = coords.getKey();
            sourceY = coords.getValue();
            System.out.println(sourceX + " " + sourceY);
        });
        piece.setOnMouseDragged(event -> {
            piece.setCenterX(event.getSceneX());
            piece.setCenterY(event.getSceneY());
        });
        piece.setOnMouseReleased(event -> {
            Pair<Integer, Integer> pos2 = coordFromPos(event.getSceneX(), event.getSceneY());
            int targetX = pos2.getKey();
            int targetY = pos2.getValue();
            MoveResult result = game.tryMakeMove(sourceX, sourceY, targetX, targetY);
            if (result.isCorrect()) {
                // Put piece on target tile
                Pair<Double, Double> pos1 = posFromCoord(targetX, targetY);
                piece.setCenterX(pos1.getKey());
                piece.setCenterY(pos1.getValue());
                piece.setPos(targetX, targetY);
                Pair<Integer, Integer> maybeKilled = result.getKilledPos();

                // Possibly delete killed piece
                if (maybeKilled != null) {
                    for(Piece piece1 : pieces){
                        if (piece1.x == maybeKilled.getKey() && piece1.y == maybeKilled.getValue()){
                            root.getChildren().remove(piece1);
                        }
                    }
                }
                // Possibly promote
                if (result.isPromoted())
                    piece.promote();
            } else {
                // Put piece back on target tile
                Pair<Double, Double> pos1 = posFromCoord(sourceX, sourceY);
                piece.setCenterX(pos1.getKey());
                piece.setCenterY(pos1.getValue());
            }
        });

        // return programmed piece
        return piece;
    }

    Pair<Integer, Integer> coordFromPos(double x, double y) {
        return new Pair<>((int) y / CELL_SIZE, (int) x / CELL_SIZE);
    }

    Pair<Double, Double> posFromCoord(int x, int y) {
        return new Pair<>(y * CELL_SIZE + CELL_SIZE / 2.0, x * CELL_SIZE + CELL_SIZE / 2.0);
    }
}
