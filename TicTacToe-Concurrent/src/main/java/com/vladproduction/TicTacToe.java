package com.vladproduction;

public interface TicTacToe {

    void setMark(int x, int y, char mark);
    char[][] table();
    void switchPlayer();
    char getCurrentPlayer();
    void setGameOver();
    boolean isGameOver();

}
