package com.example.checkersjavafx;

import com.example.checkersjavafx.controller.BoardController;
import com.example.checkersjavafx.model.Board;
import com.example.checkersjavafx.model.Game;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class CheckersApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Board.Piece[][] pieces = new Board.Piece[8][8];
        Board board = new Board(pieces);
        Game game = new Game(board);
        BoardController controller = new BoardController(game);
        Group root = controller.getRoot();

        Scene scene = new Scene(root, BoardController.GRID_SIZE * BoardController.CELL_SIZE, BoardController.GRID_SIZE * BoardController.CELL_SIZE);
        stage.setScene(scene);
        stage.setTitle("Checkers Game");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}