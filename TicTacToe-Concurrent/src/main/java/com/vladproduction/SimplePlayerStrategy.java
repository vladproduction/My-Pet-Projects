package com.vladproduction;

public class SimplePlayerStrategy implements PlayerStrategy{

    @Override
    public Move computeMove(char mark, TicTacToe ticTacToe) {

        char[][] table = ticTacToe.table();

        // Simple strategy: find first empty cell
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j] == ' ') {
                    return new Move(i, j);
                }
            }
        }
        throw new IllegalStateException("No valid moves available");
    }
}
