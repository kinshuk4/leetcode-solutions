package com.vaani.leetcode.dp;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 1235. Maximum Profit in Job Scheduling
 * Hard
 * <p>
 * 1960
 * <p>
 * 20
 * <p>
 * Add to List
 * <p>
 * Share
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
 * <p>
 * You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.
 * <p>
 * If you choose a job that ends at time X you will be able to start another job that starts at time X.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 * Output: 120
 * Explanation: The subset chosen is the first and fourth job.
 * Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
 * Output: 150
 * Explanation: The subset chosen is the first, fourth and fifth job.
 * Profit obtained 150 = 20 + 70 + 60.
 * Example 3:
 * <p>
 * <p>
 * <p>
 * Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
 * Output: 6
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
 * 1 <= startTime[i] < endTime[i] <= 10^9
 * 1 <= profit[i] <= 10^4
 */
public class MaximumProfitInJobScheduling {
    /**
     * Since our jobs is already sorted in increasing order by startTime.
     * So we can binary search to find the job next j, so that job[j].startTime >= job[i].endTime.
     */

    private static class Job {
        private final int startTime;
        private final int endTime;
        private final int profit;

        public Job(int st, int et, int pf) {
            this.startTime = st;
            this.endTime = et;
            this.profit = pf;
        }
    }


    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        List<Job> jobs = IntStream.range(0, n).mapToObj(i -> new Job(startTime[i], endTime[i], profit[i])).
                sorted(Comparator.comparingInt(a -> a.endTime)).collect(Collectors.toList());

        TreeMap<Integer, Integer> map = new TreeMap<>();
        int ans = 0;

        for (Job job : jobs) {
            Integer entryStart = map.floorKey(job.startTime);
            int maxProfit = entryStart == null ? 0 : map.get(entryStart);
            ans = Math.max(ans, maxProfit + job.profit);
            map.put(job.endTime, ans);
        }
        return ans;

    }
}
