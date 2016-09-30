package org.swati.leetcode.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a text document and a query, your goal is to find the shortest contiguous snippet of the document that contains all the words of the query. "Shortest" is measured by word length. For example, for the document
 In the small dog park, the big dog jumping over the small dog caused big laughs.
 and the query
 big small dog
 the shortest snippet is "small dog caused big".
 Since we're working in a search engine, you won't just be given the document as a string, because it's stored in an inverted index. An inverted index maps words to their positions in the documents. For the example document, the positions are
 In the small dog park, the big dog jumping over the small dog caused big laughs.
 0   1    2    3   4     5   6   7     8     9   10   11   12    13   14    15
 so the inverted index would hold
 big: [6, 14]
 caused: [13]
 dog: [3, 7, 12]
 ...
 small: [2, 11]
 the: [1, 5, 10]
 Your function will be passed a list of lists of positions, one for each query word. For the example problem, your input would be
 [ [6, 14], [2, 11], [3, 7, 12] ]
 *
 */
public class ShortestSnippet {
    private class Tuple {
        public int start = 0;
        public int end = 0;
        int minIndex = -1;
    }

    /*
    "big" => [6, 14]
    "small" => [2, 11]
    "dog" => [3, 7, 12]
     */

    private Tuple getShortestSnippet(List<List<Integer>> qsPos) {
        int numOfQueryWords = qsPos.size();
        int[] valArr = new int[numOfQueryWords];
        int[] indexArr = new int[numOfQueryWords];
        int[] sizeOfAllArrays = new int[numOfQueryWords];

        int indexOfMinValue = 0;
        int minLength = Integer.MAX_VALUE;
        Tuple tuple = new Tuple();

        //initialize all the arrays

        //put the first elements from all query arrays
        for (int i = 0; i < numOfQueryWords; i++) {
            valArr[i] = qsPos.get(i).get(0);
        }
        //put the index of the first element from all query arrays
        for (int i = 0; i < numOfQueryWords; i++) {
            indexArr[i] = 0;
        }
        //put the size of all query arrays
        for (int i = 0; i < numOfQueryWords; i++) {
            sizeOfAllArrays[i] = qsPos.get(i).size();
        }

        while (indexArr[indexOfMinValue] < sizeOfAllArrays[indexOfMinValue]) {
            valArr[indexOfMinValue] = qsPos.get(indexOfMinValue).get(indexArr[indexOfMinValue]);
            Tuple currTuple = new Tuple();
            int currMinLength = getMinLength(valArr, currTuple);
            if (minLength > currMinLength) {
                minLength = currMinLength;
                tuple.start = currTuple.start;
                tuple.end = currTuple.end;
            }
            indexArr[currTuple.minIndex] += 1;
            indexOfMinValue = currTuple.minIndex;
        }
        return tuple;
    }

    private int getMinLength(int[] valArr, Tuple tuple) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int minIndex = -1;

        for (int i = 0; i < valArr.length; i++) {
            if (valArr[i] < min) {
                min = valArr[i];
                minIndex = i;
            }

            if (valArr[i] > max) {
                max = valArr[i];
            }
        }

        tuple.start = min;
        tuple.end = max;
        tuple.minIndex = minIndex;

        return max - min;
    }

    public static void main(String[] args) {
        ShortestSnippet snippet = new ShortestSnippet();
        List<List<Integer>> indexList = new ArrayList<>();
        indexList.add(Arrays.asList(6, 14));
        indexList.add(Arrays.asList(2, 11));
        indexList.add(Arrays.asList(3, 7, 12));

        Tuple t = snippet.getShortestSnippet(indexList);
        System.out.println("Start:" + t.start
                + " End:" + t.end + " min_length:" + (t.end - t.start));
    }
}
