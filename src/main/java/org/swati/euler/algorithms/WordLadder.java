package org.swati.euler.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

 Only one letter can be changed at a time
 Each intermediate word must exist in the word list
 For example,

 Given:
 beginWord = "hit"
 endWord = "cog"
 wordList = ["hot","dot","dog","lot","log"]

 Return
 [
 ["hit","hot","dot","dog","cog"],
 ["hit","hot","lot","log","cog"]
 ]

 *
 * @author Swati Kumar
 * @since 1.0.0
 */

/**
 * Basically it's a modified breadth first search.
 * Remember when trying to find the shortest distance from one node to another - breadth first search is the best.
 */
public class WordLadder {

    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> chains = new ArrayList<List<String>>();
        Node beginNode = new Node(beginWord, null, 1);
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(beginNode);
        wordList.add(endWord);

        HashSet<String> visited = new HashSet<String>();
        HashSet<String> unvisited = new HashSet<String>();

        unvisited.addAll(wordList);
        int minimumSteps = 0;
        int prevLevel = 0;

        while(!queue.isEmpty()) {
            Node top = queue.remove();
            String word = top.getValue();
            int currLevel = top.getLevel();

            if (word.equals(endWord)) {
                if (minimumSteps == 0) {
                    minimumSteps = currLevel;
                }

                if (currLevel == minimumSteps) {
                    List<String> chain = new ArrayList<String>();
                    chain.add(top.getValue());
                    while (top.getParent() != null) {
                        //add the parent to the top and push the remaining after that
                        chain.add(0, top.getParent().getValue());
                        top = top.getParent();
                    }
                    chains.add(chain);
                    continue;
                }
            }
            //remove the visited nodes only when the current node being evaluated is on the next level.
            //If it's on the same level, then don't remove as it's a new list altogether
            if(prevLevel < currLevel){
                unvisited.removeAll(visited);
            }
            prevLevel = currLevel;

            char[] arrWord = word.toCharArray();
            for (char letter = 'a'; letter <= 'z'; letter++) {
                for (int i = 0; i < arrWord.length; i++) {
                    char store = arrWord[i];
                    if (store != letter) {
                        arrWord[i] = letter;
                    }
                    String givenWord = new String(arrWord);
                    if (unvisited.contains(givenWord)) {
                        visited.add(givenWord);
                        queue.add(new Node(givenWord, top, top.getLevel() + 1));
                    }
                    arrWord[i] = store;
                }
            }
        }
        return chains;
    }

    public class Node {
        String value;
        Node parent;
        int level;

        public Node(String value, Node parent, int level) {
            this.parent = parent;
            this.value = value;
            this.level = level;
        }

        public Node getParent() {
            return parent;
        }

        public String getValue() {
            return value;
        }

        public int getLevel() {
            return level;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public static void main(String args[]) {
        WordLadder wordLadder = new WordLadder();
        Set<String> wordList = new HashSet<String>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordLadder.findLadders("hit", "cog", wordList);
    }

}
