package org.swati.leetcode.algorithms.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement a trie with insert, search, and startsWith methods.
 *
 * @author Swati Kumar
 * @since 1.0.0
 */

class TrieNode {
    // Initialize your data structure here.
    Character value;
    Map<Character, TrieNode> childMap;
    boolean isEnd;

    public TrieNode() {
        this.value = null;
        this.childMap = new HashMap<Character, TrieNode>();
        this.isEnd = false;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        if (word != null && word.length() > 0) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (node.childMap.get(c) != null) {
                    node = node.childMap.get(c);
                } else {
                    TrieNode newNode = new TrieNode();
                    newNode.value = c;
                    node.childMap.put(c, newNode);
                    node = newNode;
                }
            }
            node.isEnd = true;
        }
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        if (word != null && word.length() > 0) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.childMap.get(c) == null) {
                    return false;
                }
                node = node.childMap.get(c);
            }
            return node.isEnd;
        }
        return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if (prefix != null && prefix.length() > 0) {
            TrieNode node = root;
            for (char c : prefix.toCharArray()) {
                if (node.childMap.get(c) == null) {
                    return false;
                }
                node = node.childMap.get(c);
            }
            return true;
        }
        return false;
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");
