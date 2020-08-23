package com.vaani.leetcode.greedy;

/**
 * https://leetcode.com/problems/water-bottles/
 * 1518. Water Bottles
 * Easy
 * <p>
 * Given numBottles full water bottles, you can exchange numExchange empty water bottles for one full water bottle.
 * <p>
 * The operation of drinking a full water bottle turns it into an empty bottle.
 * <p>
 * Return the maximum number of water bottles you can drink.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: numBottles = 9, numExchange = 3
 * Output: 13
 * Explanation: You can exchange 3 empty bottles to get 1 full water bottle.
 * Number of water bottles you can drink: 9 + 3 + 1 = 13.
 * <p>
 * Example 2:
 * <p>
 * Input: numBottles = 15, numExchange = 4
 * Output: 19
 * Explanation: You can exchange 4 empty bottles to get 1 full water bottle.
 * Number of water bottles you can drink: 15 + 3 + 1 = 19.
 * <p>
 * Example 3:
 * <p>
 * Input: numBottles = 5, numExchange = 5
 * Output: 6
 * <p>
 * Example 4:
 * <p>
 * Input: numBottles = 2, numExchange = 3
 * Output: 2
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= numBottles <= 100
 * 2 <= numExchange <= 100
 */
public class WaterBottles {
    public int numWaterBottles(int numBottles, int numExchange) {
        int ans = numBottles;
        while (numBottles >= numExchange) {
            int remainder = numBottles % numExchange;
            numBottles /= numExchange;
            ans += numBottles;
            numBottles += remainder;
        }
        return ans;
    }
}
