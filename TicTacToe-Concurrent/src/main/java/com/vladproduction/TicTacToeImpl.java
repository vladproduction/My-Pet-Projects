package com.vladproduction;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicTacToeImpl implements TicTacToe{

    private final char[][] board = new char[3][3];
    private final Lock lock = new ReentrantLock();
    private volatile char currentPlayer = 'X';
    private volatile boolean gameOver = false;

    //build board 3 x 3
    public TicTacToeImpl(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    @Override
    public void setMark(int x, int y, char mark) {
        lock.lock();
        try{

            if(board[x][y] != ' '){
                throw new IllegalArgumentException("Cell is already occupied!");
            }

            board[x][y] = mark;

        }finally {
            lock.unlock();
        }
    }

    @Override
    public char[][] table() {
        lock.lock();
        try {

            char[][] copy = new char[3][3];
            for (int i = 0; i < 3; i++) {
                System.arraycopy(board[i], 0, copy[i], 0, 3);
            }
            return copy;

        }finally {
            lock.unlock();
        }
    }

    @Override
    public void switchPlayer() {
        currentPlayer = (currentPlayer== 'X') ? 'O' : 'X';
    }

    @Override
    public char getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public void setGameOver() {
        gameOver = true;
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }
}



















