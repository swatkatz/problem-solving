package org.swati.problemSolving;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * multiples of 4 give sequence:
 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68, 72, 76, ...

 multiples of 6 give sequence:
 6, 12, 18, 24, 30, 36, 42, 48, 54, 60, 66, 72, ...

 Combine 2 sequences (but eliminate duplicates):
 4, 6, 8, 12, 16, 18, 20, 24, 28, 30, 32, 36, 40, 42, 44, 48, 52, 54, 56, 60, 64, 66, 68, 72, ...

 The task is to get the Nth element from combined sequence.
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class MultipleSequenceNthElement {
    //Time complexity O(m + nlogm) - first addition to the queue and then logm iteration of the queue n times.
    public int getNthElement(int[] arr, int n) {
        Set<Integer> uniqueAnswers = new HashSet<Integer>();
        int max = Integer.MIN_VALUE;
        Queue<Pair> pairPriorityQ = new PriorityQueue<Pair>(new PairComparator());
        for (int i = 0; i < arr.length; i++) {
            pairPriorityQ.add(new Pair(arr[i], i));
        }

        while (uniqueAnswers.size() < n) {
            int candidate = getCandidate(pairPriorityQ, arr);
            if (!uniqueAnswers.contains(candidate)) {
                uniqueAnswers.add(candidate);
                max = Math.max(max, candidate);
            }
        }
        return max;
    }

    private int getCandidate(Queue<Pair> pairPriorityQ, int[] arr) {
        Pair minPair = pairPriorityQ.poll();
        Pair newPair = new Pair(minPair.multipleVal + arr[minPair.arrPos], minPair.arrPos);
        pairPriorityQ.offer(newPair);
        return minPair.multipleVal;
    }

    class Pair {
        public int multipleVal;
        public int arrPos;

        public Pair(int multipleVal, int arrPos) {
            this.multipleVal = multipleVal;
            this.arrPos = arrPos;
        }
    }

    class PairComparator implements Comparator<Pair> {

        public int compare(Pair o1, Pair o2) {
            if (o1.multipleVal != o2.multipleVal) {
                return o1.multipleVal - o2.multipleVal;
            }
            return o1.arrPos - o2.arrPos;
        }
    }

    public static void main(String[] args) {
        MultipleSequenceNthElement multipleSequenceNthElement = new MultipleSequenceNthElement();
        int[] arr = {3, 4, 6};
        int n = 6;
        System.out.println("the " + n + " th element of sequence is " + multipleSequenceNthElement.getNthElement(arr, n));
    }
}
