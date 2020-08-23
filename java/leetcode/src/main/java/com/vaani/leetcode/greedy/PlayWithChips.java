package com.vaani.leetcode.greedy;

/**
 * https://leetcode.com/problems/play-with-chips/
 * 1217. Play with Chips
 * Easy
 * <p>
 * There are some chips, and the i-th chip is at position chips[i].
 * <p>
 * You can perform any of the two following types of moves any number of times (possibly zero) on any chip:
 * <p>
 * Move the i-th chip by 2 units to the left or to the right with a cost of 0.
 * Move the i-th chip by 1 unit to the left or to the right with a cost of 1.
 * <p>
 * There can be two or more chips at the same position initially.
 * <p>
 * Return the minimum cost needed to move all the chips to the same position (any position).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: chips = [1,2,3]
 * Output: 1
 * Explanation: Second chip will be moved to positon 3 with cost 1. First chip will be moved to position 3 with cost 0. Total cost is 1.
 * <p>
 * Example 2:
 * <p>
 * Input: chips = [2,2,2,3,3]
 * Output: 2
 * Explanation: Both fourth and fifth chip will be moved to position two with cost 1. Total minimum cost will be 2.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= chips.length <= 100
 * 1 <= chips[i] <= 10^9
 */
/*
After figuring out the meaning of "i-th chip is at position chips[i]",
for [1, 2, 3], would look like:

position 1 	position 2 	position 3
chip 1 	chip 2 	chip 3

for [2, 2, 2, 3, 3], would look like:

position 2 	position 3
chip 1 	chip 4
chip 2 	chip 5
chip 3
 */
public class PlayWithChips {
    public int minCostToMoveChips(int[] chips) {
        int odd = 0, even = 0;
        for (int c : chips) {
            if (c % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }
        return Math.min(even, odd);
    }
}
