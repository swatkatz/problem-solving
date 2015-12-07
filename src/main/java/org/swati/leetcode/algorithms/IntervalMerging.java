package org.swati.leetcode.algorithms;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Merge intervals without using extra space and extra time
 *
 * Given a collection of intervals, merge all overlapping intervals.
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 *
 * @author Swati Kumar
 * @since 1.0.0
 */

public class IntervalMerging {
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.isEmpty()) {
            return intervals;
        }
        int j = 0;
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval o1, Interval o2) {
                return o1.getStart() - o2.getStart();
            }
        });
        for (int i = 1; i < intervals.size(); i++) {
            //can be merged
            if (j < intervals.size() && intervals.get(j).getEnd() >= intervals.get(i).getStart()) {
                Interval newInterval = new Interval(intervals.get(j).getStart(), Math.max(intervals.get(j).getEnd(), intervals.get(i).getEnd()));
                intervals.set(j, newInterval);
            } else {
                intervals.set(j + 1, intervals.get(i));
                j++;
            }
        }
        return intervals.subList(0, j + 1);

    }

    class Interval {
        int start;
        int end;

        Interval(int s, int e) { start = s; end = e; }
        public int getStart() {
            return this.start;
        }
        public int getEnd() {
            return this.end;
        }
    }
}
