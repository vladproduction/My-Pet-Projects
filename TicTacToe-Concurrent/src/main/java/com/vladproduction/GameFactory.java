package com.vladproduction;

public class GameFactory {

    public static TicTacToe buildGame(){
        return new TicTacToeImpl();
    }

    public static Player createPlayer(TicTacToe ticTacToe, char mark, PlayerStrategy playerStrategy){
        return new PlayerImpl(ticTacToe, mark, playerStrategy);
    }

}
