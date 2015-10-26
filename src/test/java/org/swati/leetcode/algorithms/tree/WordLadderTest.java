package org.swati.leetcode.algorithms.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.swati.org.swati.leetcode.algorithms.tree.WordLadder;

/**
 * Add tests for WordLadder
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class WordLadderTest {
    WordLadder wordLadder = new WordLadder();

    @Test
    public void testWordLadder() {
        Set<String> wordList = new HashSet<String>();
        wordList.add("can");
        wordList.add("rap");
        wordList.add("ran");
        wordList.add("cap");
        List<List<String>> chains = wordLadder.findLadders("can", "rap", wordList);
        assertNotNull(chains);
        assertEquals(2, chains.size());
        assertEquals(3, chains.get(0).size());
        assertEquals(3, chains.get(1).size());

    }
}