package org.swati.org.swati.leetcode.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given n points on a 2D plane,
 * find the maximum number of points that lie on the same straight line.
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class MaxPointsOnALine {
    private static final double MAX_SLOPE = Integer.MAX_VALUE;//represents verticle slope

    public int maxPoints(Point[] points) {
        int maxNumberOfPoints = 0;
        if (points.length == 1) {
            return 1;
        }
        for (int i = 0; i < points.length; i++) {
            Map<Double, Integer> slopeCountForAGivenPoint = new HashMap<Double, Integer>();
            //The given point itself
            int duplicate = 1;
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    //the 2 points are duplicates
                    if (points[i].equals(points[j])) {
                        duplicate++;
                    } else {
                        double slopeVal;
                        if (points[i].getX() == points[j].getX()) {
                            //the are on the verticle line
                            slopeVal = MAX_SLOPE;
                        } else if (points[i].getY() == points[j].getY()) {
                            slopeVal = 0;
                        } else {
                            //slope is given as (y2-y1)/(x2-x1) <- make sure that conversion to double happens
                            slopeVal = (double) (points[i].getY() - points[j].getY()) / (double) (points[i].getX() - points[j].getX());
                        }

                        if (slopeCountForAGivenPoint.get(slopeVal) != null) {
                            slopeCountForAGivenPoint.put(slopeVal, slopeCountForAGivenPoint.get(slopeVal) + 1);
                        } else {
                            slopeCountForAGivenPoint.put(slopeVal, 1);
                        }
                    }
                }
            }
            int maxCountForThisPoint = duplicate;
            if (!slopeCountForAGivenPoint.values().isEmpty()) {
                List<Integer> slopeCountForAGivenPointList = new ArrayList<Integer>(slopeCountForAGivenPoint.values());
                Collections.sort(slopeCountForAGivenPointList);
                maxCountForThisPoint += slopeCountForAGivenPointList.get(slopeCountForAGivenPointList.size() - 1);
            }
            if (maxNumberOfPoints < maxCountForThisPoint) {
                maxNumberOfPoints = maxCountForThisPoint;
            }
        }
        return maxNumberOfPoints;
    }

    /**
     * Definition for a point.
     */
    public static class Point {
        private int x;
        private int y;

        public Point(int a, int b) {
            x = a; y = b;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

    public static void main(String[] args) {
        MaxPointsOnALine maxPointsOnALine = new MaxPointsOnALine();
        //[[84,250],[0,0],[1,0],[0,-70],[0,-70],[1,-1],[21,10],[42,90],[-42,-230]]
        Point[] points = new Point[9];
        points[0] = new Point(84, 250);
        points[1] = new Point(0, 0);
        points[2] = new Point(1, 0);
        points[3] = new Point(0, -70);
        points[4] = new Point(0, -70);
        points[5] = new Point(1, -1);
        points[6] = new Point(21, 10);
        points[7] = new Point(42, 90);
        points[8] = new Point(42, -230);
        System.out.println("Max points on a line from the set of points are " + maxPointsOnALine.maxPoints(points));
    }

}
