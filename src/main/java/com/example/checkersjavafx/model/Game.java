package com.example.checkersjavafx.model;

public class Game {
    public enum State {
        WhiteMoves,
        BlackMoves,
        WhiteWon,
        BlackWon,
    }

    State state = State.WhiteMoves;
    Board board;

    public Game(Board brd) {
        board = brd;
    }

    public MoveResult tryMakeMove(int srcX, int srcY, int tgtX, int tgtY) {
        MoveResult result = new MoveResult(false);
        // determine who moves depending on state
        if(getState() == State.WhiteMoves){
            result = board.makeMoveIfValid(srcX, srcY, tgtX, tgtY, true);
            if(!board.checkContainsBlackPiece()){
                state = State.WhiteWon;
            }
        }
        else if(getState() == State.BlackMoves){
            result = board.makeMoveIfValid(srcX, srcY, tgtX, tgtY, false);
            if(!board.checkContainsWhitePiece()){
                state = State.BlackWon;
            }
        }

        if(result.isCorrect() && !(state == State.WhiteWon || state == State.BlackWon)) {
            state = state == State.BlackMoves ? State.WhiteMoves : State.BlackMoves;
        }

        return result;
    }

    public State getState() {
        return state;
    }

    public Board.Piece[][] getPieces() {
        return board.pieces;
    }
}
