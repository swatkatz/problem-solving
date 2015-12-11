package org.swati.games.tictactoe;

import java.util.ArrayList;
import java.util.List;

/**
 * The Board for Tic Tac Toe
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class Board {
    private static final Integer MAX_ROW = 3;
    private static final Integer MAX_COL = 3;

    private int currRow;
    private int currCol;
    private final Cell[][] cells;

    /**
     * Allocate memory and initialize stuff
     */
    public Board() {
        this.cells = new Cell[MAX_ROW][MAX_COL];
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    /**
     * Clear the board and make it pristine
     */
    public void initBoard() {
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                cells[i][j].setContent(Seed.EMPTY);
            }
        }
    }

    public void setCurrRow(int currRow) {
        this.currRow = currRow;
    }

    public void setCurrCol(int currCol) {
        this.currCol = currCol;
    }

    public int getCurrRow() {
        return currRow;
    }

    public int getCurrCol() {
        return currCol;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public boolean hasWon(Seed currSeed) {
        //If any row has all the same seeds
        for (int i = 0; i < MAX_ROW; i++) {
            if (isContentSame(currSeed, cells[i][0], cells[i][1], cells[i][2])) {
                return true;
            }
        }
        //If any column has all the same seeds
        for (int j = 0; j < MAX_COL; j++) {
            if (isContentSame(currSeed, cells[0][j], cells[1][j], cells[2][j])) {
                return true;
            }
        }
        //if any diagonal has all the same seeds
        if (isContentSame(currSeed, cells[0][0], cells[1][1], cells[2][2])) {
            return true;
        }

        if (isContentSame(currSeed, cells[2][0], cells[1][1], cells[0][2])) {
            return true;
        }
        return false;
    }

    private boolean isContentSame(Seed seedToCheck, Cell cell1, Cell cell2, Cell cell3) {
        return seedToCheck == cell1.getContent()
                && cell1.getContent() == cell2.getContent()
                && cell2.getContent() == cell3.getContent();
    }

    public boolean isDraw() {
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                if (cells[i][j].getContent() == Seed.EMPTY) {
                    return false;
                }
            }
        }
        //no more cells left to play
        return true;
    }

    public boolean isValid(int row, int col) {
        return row < MAX_ROW && row >= 0 &&
                col < MAX_COL && col >= 0 &&
                cells[row][col].getContent() == Seed.EMPTY;
    }

    public List<int[]> getEmptyMoves() {
        List<int[]> moves = new ArrayList<int[]>();
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                if (cells[i][j].getContent() == Seed.EMPTY) {
                    moves.add(new int[]{i, j});
                }
            }
        }
        return moves;
    }

    /** Paint itself */
    public void paint() {
        for (int row = 0; row < MAX_ROW; ++row) {
            for (int col = 0; col < MAX_COL; ++col) {
                cells[row][col].paint();   // each cell paints itself
                if (col < MAX_COL - 1) System.out.print("|");
            }
            System.out.println();
            if (row < MAX_ROW - 1) {
                System.out.println("-----------");
            }
        }
    }
}
