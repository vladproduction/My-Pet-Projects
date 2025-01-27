package com.vladproduction;

public class PlayerImpl implements Player {

    private final TicTacToe game;
    private final char mark;
    private final PlayerStrategy playerStrategy;
    private final Object turnLock = new Object();

    public PlayerImpl(TicTacToe game, char mark, PlayerStrategy playerStrategy) {
        this.game = game;
        this.mark = mark;
        this.playerStrategy = playerStrategy;
    }

    @Override
    public void run() {
        while (!game.isGameOver()){

            synchronized (turnLock){
                if(game.getCurrentPlayer() == mark && !game.isGameOver()){
                    Move move = playerStrategy.computeMove(mark, game);

                    try {
                        game.setMark(move.row, move.column, mark);

                        //check if this move has won the game
                        if(hasWon(game.table(), mark)){
                            game.setGameOver();
                        } else if (isBoardFull(game.table())) {
                            game.setGameOver();
                        }
                        
                        game.switchPlayer();

                    }catch (IllegalArgumentException e){
                        continue; //try another move

                    }

                    //delay to prevent cpu overuse
                    try {
                        Thread.sleep(10);
                    }catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                        break;
                    }

                }
            }
        }
    }

    //helper to check if any row, column or diagonal is marked
    private boolean hasWon(char[][] table, char mark) {
        //checking row
        for (int i = 0; i < 3; i++) {
            if(table[i][0] == mark && table[i][1] == mark && table[i][2] == mark){
                return true;
            }
        }
        //checking column
        for (int j = 0; j < 3; j++) {
            if(table[0][j] == mark && table[1][j] == mark && table[2][j] == mark){
                return true;
            }
        }
        //checking diagonal
        if(table[0][0] == mark && table[1][1] == mark && table[2][2] == mark){
            return true;
        }
        return table[0][2] == mark && table[1][1] == mark && table[2][0] == mark;
    }

    //helper to check if all cells are already full of marks
    private boolean isBoardFull(char[][] table) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(table[i][j] == ' '){
                    return false;
                }
            }
        }
        return true;
    }

}
