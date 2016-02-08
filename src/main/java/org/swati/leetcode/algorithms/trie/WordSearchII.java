package org.swati.leetcode.algorithms.trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This makes use of the trie class
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class WordSearchII {
    Set<String> result = new HashSet<String>();

    public List<String> findWords(char[][] board, String[] words) {
        //HashSet<String> result = new HashSet<String>();

        Trie trie = new Trie();
        for(String word: words){
            trie.insert(word);
        }

        int m=board.length;
        int n=board[0].length;

        boolean[][] visited = new boolean[m][n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                dfs(board, visited, "", i, j, trie);
            }
        }

        return new ArrayList<String>(result);
    }

    public void dfs(char[][] board, boolean[][] visited, String str, int i, int j, Trie trie){
        int m=board.length;
        int n=board[0].length;

        if(i<0 || j<0||i>=m||j>=n){
            return;
        }

        if(visited[i][j])
            return;

        str = str + board[i][j];

        if(!trie.startsWith(str))
            return;

        if(trie.search(str)){
            result.add(str);
        }

        visited[i][j]=true;
        dfs(board, visited, str, i-1, j, trie);
        dfs(board, visited, str, i+1, j, trie);
        dfs(board, visited, str, i, j-1, trie);
        dfs(board, visited, str, i, j+1, trie);
        visited[i][j]=false;
    }



    public static void main(String args[]) {
        WordSearchII wordSearchII = new WordSearchII();
        char[][] board =
                {        {'o', 'a', 'a', 'n'},
                         {'e', 't', 'a', 'e'},
                         {'i', 'h', 'k', 'r'},
                         {'i', 'f', 'l', 'v'}
                };
        String [] words = {"oath", "pea", "eat", "rain"};
        List<String> result = wordSearchII.findWords(board, words);
        for (String eachWord : result) {
            System.out.println(eachWord);
        }
    }
}
