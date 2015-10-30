package org.swati.problemSolving;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * You're given an array of CSV strings representing search results.
 * Results are sorted by a score initially.
 * A given host may have several listings that show up in these results.
 * Suppose we want to show 12 results per page, but we don't want the same host to dominate the results.
 * Write a function that will reorder the list so that a host shows up at most once on a page if possible,
 * but otherwise preserves the ordering.
 * Your program should return the new array and print out the results in blocks representing the pages.
 *
 * @author swkumar (swkumar@groupon.com)
 * @since 1.0.0
 */
public class Pagination {

    public List<String> getSortedAndPaginatedResults(String[] input, int pageSize) {
        if (input.length <= pageSize) {
            return new ArrayList<String>(Arrays.asList(input));
        }
        LinkedHashMap<Integer, String> sortedPage = new LinkedHashMap<Integer, String>();
        List<String> duplicates = new ArrayList<String>();
        List<String> output = new ArrayList<String>(input.length);
        output.add(input[0]);
        for (int i = 1; i < input.length; i++) {
            Integer hostId = Integer.parseInt(input[i].split(",")[0]);
            if (sortedPage.size() != 0 && sortedPage.size() % pageSize == 0) {
                output.addAll(sortedPage.values());
                sortedPage = new LinkedHashMap<Integer, String>();
                List<String> dupCopy = new ArrayList<String>(duplicates);
                for (String dupStr : dupCopy) {
                    Integer dupHostId = Integer.parseInt(dupStr.split(",")[0]);
                    if (sortedPage.get(dupHostId) == null) {
                        sortedPage.put(dupHostId, dupStr);
                        duplicates.remove(dupStr);
                    }
                }
            }
            if (sortedPage.get(hostId) == null) {
                sortedPage.put(hostId, input[i]);
            } else {
                duplicates.add(input[i]);
            }
        }
        List<String> mergeList = new ArrayList<String>();
        mergeList.addAll(duplicates);
        mergeList.addAll(sortedPage.values());
        Collections.sort(mergeList, new Comparator<String>() {
            public int compare(String o1, String o2) {
                if (Double.parseDouble(o2.split(",")[2]) > Double.parseDouble(o1.split(",")[2])) {
                    return 1;
                } else if (Double.parseDouble(o2.split(",")[2]) == Double.parseDouble(o1.split(",")[2])) {
                    return 0;
                }
                return -1;
            }
        });
        output.addAll(mergeList);
        return output;
    }

    public static void main(String args[]) {
        Pagination pagination = new Pagination();
        String[] input = {"host_id,listing_id,score,city",
                "1,28,300.1,San Francisco",
                "4,5,209.1,San Francisco",
                "20,7,208.1,San Francisco",
                "23,8,207.1,San Francisco",
                "16,10,206.1,Oakland",
                "1,16,205.1,San Francisco",
                "1,31,204.6,San Francisco",
                "6,29,204.1,San Francisco",
                "7,20,203.1,San Francisco",
                "8,21,202.1,San Francisco",
                "2,18,201.1,San Francisco",
                "2,30,200.1,San Francisco",
                "15,27,109.1,Oakland",
                "10,13,108.1,Oakland",
                "11,26,107.1,Oakland",
                "12,9,106.1,Oakland",
                "13,1,105.1,Oakland",
                "22,17,104.1,Oakland",
                "1,2,103.1,Oakland",
                "28,24,102.1,Oakland",
                "18,14,11.1,San Jose",
                "6,25,10.1,Oakland",
                "19,15,9.1,San Jose",
                "3,19,8.1,San Jose",
                "3,11,7.1,Oakland",
                "27,12,6.1,Oakland",
                "1,3,5.1,Oakland",
                "25,4,4.1,San Jose",
                "5,6,3.1,San Jose",
                "29,22,2.1,San Jose",
                "30,23,1.1,San Jose"};
        int pageSize = 12;
        List<String> output = pagination.getSortedAndPaginatedResults(input, pageSize);
        for (int i = 0; i < output.size(); i++) {
            System.out.println(output.get(i));
            if (i != 0 && i % pageSize == 0) {
                System.out.println("=======BLOCK=====");
            }
        }
    }

}
