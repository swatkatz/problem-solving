package org.swati.leetcode.algorithms.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement a trie with insert, search, and startsWith methods.
 *
 * @author Swati Kumar
 * @since 1.0.0
 */

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        SearchNode searchNode = this.searchWord(word, true);
        if (searchNode.isFound()) {
            //set the final node as end node
            searchNode.getLastNode().isEnd = true;
            return;
        }
        //The word doesn't exist
        TrieNode currNode = root;
        char[] wordArr = word.toCharArray();
        for (char valueToBeInserted : wordArr) {
            TrieNode node = currNode.getChildNode(valueToBeInserted);
            if (node != null) {
                currNode = node;
            } else {
                TrieNode newNode = new TrieNode(valueToBeInserted, false);
                currNode.setChild(newNode);
                currNode = newNode;
            }
        }
        currNode.isEnd = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        return searchWord(word, false).isFound();
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return searchWord(prefix, true).isFound();
    }

    public SearchNode searchWord(String word, boolean partial) {
        char[] wordArr = word.toCharArray();
        TrieNode currNode = root;
        if (currNode == null) {
            return new SearchNode(null, false);
        }
        for (char aWordArr : wordArr) {
            TrieNode node = currNode.getChildNode(aWordArr);
            if (node != null) {
                currNode = node;
            } else {
                return new SearchNode(currNode, false);
            }
        }
        boolean found = partial || currNode.isLeafNode();
        return new SearchNode(currNode, found);
    }

    public class SearchNode {
        TrieNode lastNode;
        boolean found;

        public SearchNode(TrieNode lastNode, boolean found) {
            this.lastNode = lastNode;
            this.found = found;
        }

        public TrieNode getLastNode() {
            return lastNode;
        }

        public boolean isFound() {
            return found;
        }
    }

    public class TrieNode {
        // Initialize your data structure here
        private char value;
        private Map<Character, TrieNode> childMap;
        private boolean isEnd;

        public TrieNode() {
            this.value = '\0';
            this.childMap = new HashMap<Character, TrieNode>();
        }

        public TrieNode(char value, boolean isEnd) {
            this.value = value;
            this.isEnd = isEnd;
            this.childMap = new HashMap<Character, TrieNode>();
        }

        public void setChild(TrieNode node) {
            this.childMap.put(node.getValue(), node);
        }

        public char getValue() {
            return value;
        }

        public TrieNode getChildNode(char value) {
            if (childMap.isEmpty()) {
                return null;
            }
            return childMap.get(value);
        }

        public boolean isLeafNode() {
            return isEnd;
        }

        public void setIsEnd(boolean isEnd) {
            this.isEnd = isEnd;
        }
    }

    public static void main(String args[]) {
        Trie trie = new Trie();
        trie.insert("food");
        trie.insert("erm");
        trie.insert("er");
        trie.insert("erlang");
        trie.insert("foodo");
        System.out.println("Trie is " + trie);

        String word = "er";
        if (trie.search(word)) {
            System.out.println(word + " is found in the trie");
        } else {
            System.out.println(word + " is not found in the trie");
        }

        if (trie.startsWith(word)) {
            System.out.println(word + " is a prefix in the trie");
        } else {
            System.out.println(word + " is not a prefix in the trie");
        }
    }
}
