package com.vaani.leetcode.binary_search;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/heaters/
 * 475. Heaters
 * Easy
 * <p>
 * Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.
 * <p>
 * Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.
 * <p>
 * So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.
 * <p>
 * Note:
 * <p>
 * Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
 * Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
 * As long as a house is in the heaters' warm radius range, it can be warmed.
 * All the heaters follow your radius standard and the warm radius will the same.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3],[2]
 * Output: 1
 * Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
 * <p>
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: [1,2,3,4],[1,4]
 * Output: 1
 * Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.
 */
/*
 Find the pre and post heater for each house
 */
public class Heaters {
    public int findRadius(int[] houses, int[] heaters) {
        if (houses == null || houses.length == 0) {
            return 0;
        }
        Arrays.sort(heaters);
        Arrays.sort(houses);
        int ans = 0;
        int j = 0;
        for (int house : houses) {
            while (j < heaters.length && heaters[j] < house) {
                j++;
            }
            int pre = j == 0 ? Integer.MAX_VALUE : house - heaters[j - 1];
            int post = j == heaters.length ? Integer.MAX_VALUE : heaters[j] - house;
            ans = Math.max(ans, Math.min(pre, post));
        }
        return ans;
    }
}
