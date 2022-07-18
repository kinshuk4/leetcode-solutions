package com.vaani.leetcode.greedy;

import lombok.ToString;
import org.junit.Assert;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/maximum-performance-of-a-team/
 * 1383. Maximum Performance of a Team
 * Hard
 * <p>
 * <p>
 * You are given two integers n and k and two integer arrays speed and efficiency both of length n. There are n engineers numbered from 1 to n. speed[i] and efficiency[i] represent the speed and efficiency of the ith engineer respectively.
 * <p>
 * Choose at most k different engineers out of the n engineers to form a team with the maximum performance.
 * <p>
 * The performance of a team is the sum of their engineers' speeds multiplied by the minimum efficiency among their engineers.
 * <p>
 * Return the maximum performance of this team. Since the answer can be a huge number, return it modulo 109 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
 * Output: 60
 * Explanation:
 * We have the maximum performance of the team by selecting engineer 2 (with speed=10 and efficiency=4) and engineer 5 (with speed=5 and efficiency=7). That is, performance = (10 + 5) * min(4, 7) = 60.
 * Example 2:
 * <p>
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
 * Output: 68
 * Explanation:
 * This is the same example as the first but k = 3. We can select engineer 1, engineer 2 and engineer 5 to get the maximum performance of the team. That is, performance = (2 + 10 + 5) * min(5, 4, 7) = 68.
 * Example 3:
 * <p>
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
 * Output: 72
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= <= k <= n <= 10^5
 * speed.length == n
 * efficiency.length == n
 * 1 <= speed[i] <= 10^5
 * 1 <= efficiency[i] <= 10^8
 */
public class MaximumPerformanceOfATeam {
    public static void main(String[] args) {
        int[] speed = {2, 10, 3, 1, 5, 8};
        int[] efficiency = {5, 4, 3, 9, 7, 2};
        Assert.assertEquals(60, new MaximumPerformanceOfATeam().maxPerformance(speed.length, speed, efficiency, 2));
    }

    final long MOD = (long) 1e9 + 7;

    @ToString
    static class Engineer {
        int speed, efficiency;

        Engineer(int speed, int efficiency) {
            this.speed = speed;
            this.efficiency = efficiency;
        }
    }

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        List<Engineer> engineers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            engineers.add(new Engineer(speed[i], efficiency[i]));
        }
        engineers.sort((a, b) -> b.efficiency - a.efficiency);

        PriorityQueue<Integer> takenPQ = new PriorityQueue<>();
        long totalSpeed = 0, max = 0;

        for (Engineer e : engineers) {
            int engSpeed = e.speed;
            takenPQ.add(engSpeed);
            totalSpeed += engSpeed;

            if (takenPQ.size() > k) {
                totalSpeed -= takenPQ.poll();
            }

            max = Math.max(max, totalSpeed * e.efficiency);
        }

        return (int) (max % MOD);
    }

}
