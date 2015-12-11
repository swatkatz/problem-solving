package org.swati.games.tictactoe;

import java.util.List;

/**
 * The Computer AI Player
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class AIPlayer {
    private final Board board;
    private final Seed me;
    private final Seed human;

    public AIPlayer(Board board, Seed me) {
        this.board = board;
        this.me = me;
        this.human = getOppPlayer();
    }

    /**
     * The appropriate row and col chosen
     * @return array of chosen row and col
     */
    public int[] move() {
        int[] result = minmax(me, 3);
        return new int[]{result[1], result[2]};
    }

    private int[] minmax(Seed currPlayer, int lookAhead) {
        List<int[]> nextMoves = getNextPossibleMoves();

        //initialize bestScore to the opposite
        int bestScore = currPlayer == me ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int bestRow = -1;
        int bestCol = -1;

        if (lookAhead == 0 || nextMoves.isEmpty()) {
            bestScore = evaluate();
        } else {
            for (int[] move : nextMoves) {
                //set this move to currPlayer
                board.getCells()[move[0]][move[1]].setContent(currPlayer);
                if (currPlayer == me) { //we want to maximize
                    int newScore = minmax(human, lookAhead - 1)[0];
                    if (bestScore < newScore) {
                        bestScore = newScore;
                        bestRow = move[0];
                        bestCol = move[1];
                    }
                } else { //we want to minimize
                    int newScore = minmax(me, lookAhead - 1)[0];
                    if (bestScore > newScore) {
                        bestScore = newScore;
                        bestRow = move[0];
                        bestCol = move[1];
                    }
                }
                //undo move
                board.getCells()[move[0]][move[1]].setContent(Seed.EMPTY);
            }
        }
        return new int[] {bestScore, bestRow, bestCol};
    }

    private List<int[]> getNextPossibleMoves() {
        return board.getEmptyMoves();
    }

    private Seed getOppPlayer() {
        return this.me == Seed.CIRCLE ? Seed.CROSS : Seed.CIRCLE;
    }

    private int evaluate() {
        int score = 0;
        score += evaluateLine(0, 0, 0, 1, 0, 2); //row0
        score += evaluateLine(1, 0, 1, 1, 1, 2); //row1
        score += evaluateLine(2, 0, 2, 1, 2, 2); //row3
        score += evaluateLine(0, 0, 1, 0, 2, 0); //col0
        score += evaluateLine(0, 1, 1, 1, 2, 1); //col1
        score += evaluateLine(0, 2, 1, 2, 2, 2); //col2
        score += evaluateLine(0, 0, 1, 1, 2, 2); //dig1
        score += evaluateLine(0, 2, 1, 1, 2, 0); //dig2
        return score;
    }

    private int evaluateLine(int row1, int col1, int row2, int col2, int row3, int col3) {
        int score = 0;
        //first cell
        if (board.getCells()[row1][col1].getContent() == me) {
            score = 1;
        } else if (board.getCells()[row1][col1].getContent() == human) {
            score = -1;
        }

        //second cell
        if (board.getCells()[row2][col2].getContent() == me) {
            //2 consecutive
            if (score == 1) {
                score = 10;
            } else if (score == -1) {
                score = 0;
            } else {
                score = 1;
            }
        } else {
            if (score == 1) {
                score = 0;
            } else if (score == -1) {
                score = -10;
            } else {
                score = -1;
            }
        }

        //third cell
        if (board.getCells()[row3][col3].getContent() == me) {
            if (score > 0) {
                score *= 10;
            } else if (score < 0) {
                score = 0;
            } else {
                score = 1;
            }
        } else {
            if (score > 1) {
                score = 0;
            } else if (score < 0) {
                score *= 10;
            } else {
                score = -1;
            }
        }
        return score;
    }

}
