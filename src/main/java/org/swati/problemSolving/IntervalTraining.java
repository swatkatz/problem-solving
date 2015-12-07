package org.swati.problemSolving;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Interval Training
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class IntervalTraining {
    private List<Interval> intervals = new ArrayList<Interval>();

    private void addInterval(Interval interval) {
        intervals.add(interval);
        //sort the list
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.getStart(), o2.getStart());
            }
        });

        //merge values that can be merged
        List<Interval> finalList = new ArrayList<Interval>();
        int i = 0;
        int j = 0;
        while(i < intervals.size()) {
            finalList.add(intervals.get(i));
            j = j + 1;
            while (j < intervals.size() && canBeMerged(intervals.get(i), intervals.get(j))) {
                Interval iInterval = intervals.get(i);
                Interval jInterval = intervals.get(j);
                iInterval.setEnd(jInterval.getEnd());
                j = j + 1;
            }
            i = j;
        }
        intervals = finalList;
    }

    private boolean canBeMerged(Interval givenInterval, Interval nextInterval) {
        return (givenInterval.getEnd() + 1 == nextInterval.getStart() || givenInterval.getEnd() >= nextInterval.getStart());
    }

    private void addIntervals() {
        addInterval(new Interval(-10, -1));
        addInterval(new Interval(-5, 1));
        addInterval(new Interval(0, 2));
        addInterval(new Interval(4, 10));
        addInterval(new Interval(21, 23));
        addInterval(new Interval(12, 20));
    }

    public static void main(String args[]) {
        IntervalTraining intervalTraining = new IntervalTraining();
        intervalTraining.addIntervals();
        for (Interval interval : intervalTraining.intervals) {
            System.out.println(interval.getStart() + " , " + interval.getEnd());
        }
    }
}
