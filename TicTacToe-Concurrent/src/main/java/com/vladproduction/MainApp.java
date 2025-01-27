package com.vladproduction;

import static java.lang.System.*;

public class MainApp {
    public static void main(String[] args) {

        //create game
        TicTacToe game = GameFactory.buildGame();

        //create strategy
        PlayerStrategy strategy = new SimplePlayerStrategy();

        //create two players
        Player playerX = GameFactory.createPlayer(game, 'X', strategy);
        Player playerO = GameFactory.createPlayer(game, 'O', strategy);

        //create and starts threads for players
        Thread threadX = new Thread(playerX);
        Thread threadO = new Thread(playerO);

        //draw bord
        out.println("Starting game");
        printBoard(game.table());

        //starting game
        threadX.start();
        threadO.start();

        //game process scenarios
        try{
            threadX.join(2000); //waiting game to complete (max 2 seconds)
            threadX.join(2000);

            //if threads haven`t finished interrupt them
            if(threadX.isAlive() || threadO.isAlive()){
                threadX.interrupt();
                threadO.interrupt();
                out.println("Game time out! (over 2 seconds)");
            }

        }catch (InterruptedException e){
            out.println("Game was interrupted!");
            threadX.interrupt(); //restore flag
            threadO.interrupt();
        }

        //print final board state
        out.println("\nFinal TicTacToe GameBoard state:");
        printBoard(game.table());



    }

    private static void printBoard(char[][] board) {
        out.println("-------------");
        for (int i = 0; i < 3; i++) {
            out.print("| ");
            for (int j = 0; j < 3; j++) {
                out.print(board[i][j] + " | ");
            }
            out.println("\n-------------");
        }
        out.println();


    }
}
