package org.swati.games;

import java.util.Random;
import java.util.Scanner;

import org.swati.games.tictactoe.AIPlayer;
import org.swati.games.tictactoe.Board;
import org.swati.games.tictactoe.GameState;
import org.swati.games.tictactoe.Seed;

/**
 * An AI for TicTacToe game
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class TicTacToe implements Game {
    private Board board;
    private GameState currState;
    private Seed currPlayer;
    private static Scanner in = new Scanner(System.in);
    private AIPlayer aiPlayer;

    public TicTacToe() {
        this.board = new Board();
        Random random = new Random();
        this.currPlayer = random.nextInt(1) == 0 ? Seed.CROSS : Seed.CIRCLE;
        this.currState = GameState.PLAYING;
        this.aiPlayer = new AIPlayer(board, currPlayer);
    }

    public void playGame() {
        board.initBoard();
        while (!gameOver()) {
            playerMove();
            board.paint();
            updateStatus();
            currPlayer = currPlayer == Seed.CIRCLE ? Seed.CROSS : Seed.CIRCLE;
        }
        String result;
        if (gameWon()) {
            result = currState == GameState.CIRCLEWIN ? "Circle won the game" : "Cross won the game";
        } else {
            result = "The game is a draw";
        }
        System.out.println(result);
    }

    public boolean gameOver() {
        return currState != GameState.PLAYING;
    }

    public boolean gameWon() {
        return currState == GameState.CIRCLEWIN || currState == GameState.CROSSWIN;
    }

    private void playerMove() {
        int row, col;
        //human is always circle
        if (currPlayer == Seed.CIRCLE) {
            do {
                System.out.println("Player O, Enter your move {row[0-2] col[0-2]} : ");
                row = in.nextInt();
                col = in.nextInt();
            } while (!board.isValid(row, col));
            board.getCells()[row][col].setContent(Seed.CIRCLE);
        } else {
            System.out.println("Computer Move: ");
            int[] computerMove = getComputerMove();
            row = computerMove[0];
            col = computerMove[1];
            board.getCells()[row][col].setContent(Seed.CROSS);
        }
        board.setCurrRow(row);
        board.setCurrCol(col);
    }

    private void updateStatus() {
        if (board.hasWon(currPlayer)) {
            currState = currPlayer == Seed.CIRCLE ? GameState.CIRCLEWIN : GameState.CROSSWIN;
        } else if (board.isDraw()) {
            currState = GameState.DRAW;
        }
    }

    private int[] getComputerMove() {
        return aiPlayer.move();
    }

    public static void main(String args[]) {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.playGame();
    }
}
