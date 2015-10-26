package org.swati.leetcode.algorithms.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * This makes use of the trie class
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class WordSearch {
    private char[][] board;
    private int iMax, jMax;
    private Trie trie = new Trie();
    private List<String> result = new ArrayList<String>();

    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        this.iMax = board.length;
        this.jMax = board[0].length;

        for (String word : words) {
            trie.insert(word);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char letter = board[i][j];
                findAllPaths(new Position(Character.toString(letter), i, j, i, j));
            }
        }
        return result;
    }

    private void findAllPaths(Position position) {
        Trie.SearchNode searchNode = trie.searchWord(position.getValue(), true);
        if (searchNode.isFound()) {
            if (searchNode.getLastNode().isLeafNode()) {
                result.add(position.getValue());
                //to avoid duplicates
                searchNode.getLastNode().setIsEnd(false);
            }
            List<Position> neighbors = getNeighbors(position);
            for (Position neighbor : neighbors) {
                findAllPaths(neighbor);
            }
        }
    }

    private List<Position> getNeighbors(Position currPosition) {
        List<Position> neighbors = new ArrayList<Position>();
        int lastIPos = currPosition.getLastI();
        int lastJPos = currPosition.getLastJ();
        if (currPosition.getI() - 1 >= 0 && currPosition.getI() - 1 != lastIPos) {
            int newIPos = currPosition.getI() - 1;
            int newJPos = currPosition.getJ();
            char newChar = board[newIPos][newJPos];
            neighbors.add(new Position(currPosition.getValue()+newChar, newIPos, newJPos, currPosition.getI(), currPosition.getJ()));
        }
        if (currPosition.getI() + 1 < iMax && currPosition.getI() + 1 != lastIPos) {
            int newIPos = currPosition.getI() + 1;
            int newJPos = currPosition.getJ();
            char newChar = board[newIPos][newJPos];
            neighbors.add(new Position(currPosition.getValue()+newChar, newIPos, newJPos, currPosition.getI(), currPosition.getJ()));
        }
        if (currPosition.getJ() - 1 >= 0 && currPosition.getJ() - 1 != lastJPos) {
            int newIPos = currPosition.getI();
            int newJPos = currPosition.getJ() - 1;
            char newChar = board[newIPos][newJPos];
            neighbors.add(new Position(currPosition.getValue()+newChar, newIPos, newJPos, currPosition.getI(), currPosition.getJ()));
        }
        if (currPosition.getJ() + 1 < jMax && currPosition.getJ() + 1 != lastJPos) {
            int newIPos = currPosition.getI();
            int newJPos = currPosition.getJ() + 1;
            char newChar = board[newIPos][newJPos];
            neighbors.add(new Position(currPosition.getValue()+newChar, newIPos, newJPos, currPosition.getI(), currPosition.getJ()));
        }
        return neighbors;
    }

    public class Position {
        public String value;
        public int i, j;
        public int lastI, lastJ;

        public Position(String letter, int i, int j, int lastI, int lastJ) {
            this.value = letter;
            this.i = i;
            this.j = j;
            this.lastI = lastI;
            this.lastJ = lastJ;
        }

        public String getValue() {
            return value;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        public int getLastI() {
            return lastI;
        }

        public int getLastJ() {
            return lastJ;
        }
    }

    public static void main(String args[]) {
        WordSearch wordSearch = new WordSearch();
        char[][] board =
                {        {'o', 'a', 'a', 'n'},
                         {'e', 't', 'a', 'e'},
                         {'i', 'h', 'k', 'r'},
                         {'i', 'f', 'l', 'v'}
                };
        String [] words = {"oath", "pea", "eat", "rain"};
        List<String> result = wordSearch.findWords(board, words);
        for (String eachWord : result) {
            System.out.println(eachWord);
        }
    }
}
