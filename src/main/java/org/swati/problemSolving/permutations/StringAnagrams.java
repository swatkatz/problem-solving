package org.swati.problemSolving.permutations;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Algorithm for finding number of anagrams of a given string, given a dictionary, assuming that the dictionary is preprocessed as a trie
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class StringAnagrams {
    Trie trie = new Trie();

    public class Trie {
        private TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(String word) {
            HashMap<Character, TrieNode> children = root.children;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                TrieNode t;
                if (children.containsKey(c)) {
                    t = children.get(c);
                } else {
                    t = new TrieNode(c);
                    children.put(c, t);
                }
                children = t.children;
                if (i == word.length() - 1) {
                    t.isLeaf = true;
                }
            }
        }

        public boolean startsWith(String prefix) {
            return searchNode(prefix) != null;
        }

        public TrieNode searchNode(String word) {
            HashMap<Character, TrieNode> children = root.children;
            TrieNode node = null;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (children.containsKey(c)) {
                    node = children.get(c);
                    children = node.children;
                } else {
                    return null;
                }
            }
            return node;
        }

    }

    public class TrieNode {
        char c;
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        boolean isLeaf;

        public TrieNode() {}

        public TrieNode(char c) {
            this.c = c;
            children = new HashMap<Character, TrieNode>();
        }
    }

    public StringAnagrams(Set<String> dictionary) {
        for (String word : dictionary) {
            trie.insert(word);
        }
    }

    public void countAnagrams(String str) {
        int count = permute("", str, 0) - 1;
        System.out.println("The anagrams are " + count);
    }

    private int permute(String prefix, String str, int count) {
        int n = str.length();
        if (n == 0) {
            return ++count;
        } else {
            for (int i = 0; i < n; i++) {
                String temp = prefix + str.charAt(i);
                if (trie.startsWith(temp)) {
                    count = permute(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), count);
                }
            }
        }
        return count;
    }

    public static void main(String args[]) {
        Set<String> dictionary = new HashSet<String>();
        dictionary.add("bad");
        dictionary.add("dab");
        dictionary.add("adb");
        dictionary.add("cdde");
        dictionary.add("bde");
        dictionary.add("dba");
        dictionary.add("bda");

        StringAnagrams stringAnagrams = new StringAnagrams(dictionary);
        stringAnagrams.countAnagrams("bda");
    }

}
