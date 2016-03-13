package org.swati.stripe;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Description
 *
 * @author swkumar (swkumar@groupon.com)
 * @since 1.0.0
 */
public class ServerNameManager {

    private Map<String, ServerAllocation> serverAllocationMap = new HashMap<String, ServerAllocation>();

    public Integer getServerNumber(Set<Integer> allocatedNumbers) {
        int max = Integer.MIN_VALUE;
        for (Integer val : allocatedNumbers) {
            if (max < val) {
                max = val;
            }
        }

        for (int i = 1; i <= max; i++) {
            if (!allocatedNumbers.contains(i)) {
                return i;
            }
        }

        return max + 1;
    }

    public String allocate(String key) {
        ServerAllocation serverAllocation;
        if (serverAllocationMap.containsKey(key)) {
            serverAllocation = serverAllocationMap.get(key);
        } else {
            serverAllocation = new ServerAllocation();
            serverAllocationMap.put(key, serverAllocation);
        }
        return key + serverAllocation.allocate();
    }

    public boolean deAllocate(String serverName) {
        String[] keyVal = getKeyAndValueFromServerName(serverName);
        if (serverAllocationMap.containsKey(keyVal[0])) {
            ServerAllocation serverAllocation = serverAllocationMap.get(keyVal[0]);
            return serverAllocation.deAllocate(Integer.parseInt(keyVal[1]));
        }
        return false;
    }

    private String[] getKeyAndValueFromServerName(String serverName) {
        StringBuilder sbKey = new StringBuilder();
        StringBuilder sbVal = new StringBuilder();
        for (char c : serverName.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                sbKey.append(c);
            } else {
                sbVal.append(c);
            }
        }
        return new String[]{sbKey.toString(), sbVal.toString()};
    }

    class ServerAllocation {
        PriorityQueue<Integer> allocated = new PriorityQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        PriorityQueue<Integer> deAllocated = new PriorityQueue<Integer>();

        public Integer allocate() {
            Integer val;
            if (allocated.isEmpty()) {
                val = 1;
            } else if (deAllocated.isEmpty()) {
                val = allocated.peek() + 1;
            } else {
                val = deAllocated.poll();
            }
            allocated.offer(val);
            return val;
        }

        public boolean deAllocate(Integer serverNumber) {
            if (allocated.remove(serverNumber)) {
                deAllocated.offer(serverNumber);
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        ServerNameManager serverNameManager = new ServerNameManager();
        System.out.println(serverNameManager.allocate("apiserver"));
        System.out.println(serverNameManager.allocate("apiserver"));
        System.out.println(serverNameManager.allocate("apiserver"));
        System.out.println(serverNameManager.deAllocate("apiserver2"));
        System.out.println(serverNameManager.deAllocate("apiserver1"));
        System.out.println(serverNameManager.allocate("apiserver"));
        System.out.println(serverNameManager.allocate("apiserver"));

        Set<Integer> set = new HashSet<Integer>();
        set.addAll(Arrays.asList(6, 2, 1, 4));
        System.out.println("server number " + serverNameManager.getServerNumber(set));
    }
}
