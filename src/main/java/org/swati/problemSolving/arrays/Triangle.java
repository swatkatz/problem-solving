package org.swati.problemSolving.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Find at most one triplet forming a triangle from the array of segments
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class Triangle {
    //should return an empty array if no triplets are found from the list of segments
    //should return any 1 triplet of segments if they exist
    int[] getTriangleSides(Integer[] segments) {
        int[] arr = new int[3];
        if (segments.length < 3) {
            return arr;
        }
        List<Integer> segmentList = Arrays.asList(segments);
        Collections.sort(segmentList);
        boolean foundTri;
        for (int i = 0; i < segmentList.size(); i++) {
            if (i < segmentList.size() - 2) {
                foundTri = isTriangle(segmentList.get(i), segmentList.get(i+1), segmentList.get(i+2));
                if (foundTri) {
                    arr[0] = segmentList.get(i);
                    arr[1] = segmentList.get(i+1);
                    arr[2] = segmentList.get(i+2);
                    break;
                }
            }
        }
        return arr;
    }

    private boolean isTriangle(int a, int b, int c) {
        return (a + b) > c;
    }

    public static void main(String args[]) {
        Integer[] segments = {5,11,2,7,1};
        Triangle triangle = new Triangle();
        int[] answer = triangle.getTriangleSides(segments);
        for (int anAnswer : answer) {
            System.out.print(", " + anAnswer);
        }
    }
}