package org.swati.games.tictactoe;

/**
 * Each cell in the board for tic-tac-toe
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class Cell {
    private int row;
    private int col;
    private Seed content;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.content = Seed.EMPTY;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setContent(Seed content) {
        this.content = content;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Seed getContent() {
        return content;
    }

    /** Paint itself */
    public void paint() {
        switch (content) {
            case CROSS:  System.out.print(" X "); break;
            case CIRCLE: System.out.print(" O "); break;
            case EMPTY:  System.out.print("   "); break;
        }
    }
}
