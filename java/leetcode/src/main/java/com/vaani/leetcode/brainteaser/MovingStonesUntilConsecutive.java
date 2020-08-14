package com.vaani.leetcode.brainteaser;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/moving-stones-until-consecutive/
 * 1033. Moving Stones Until Consecutive
 * Easy
 * <p>
 * Three stones are on a number line at positions a, b, and c.
 * <p>
 * Each turn, you pick up a stone at an endpoint (ie., either the lowest or highest position stone), and move it to an unoccupied position between those endpoints.  Formally, let's say the stones are currently at positions x, y, z with x < y < z.  You pick up the stone at either position x or position z, and move that stone to an integer position k, with x < k < z and k != y.
 * <p>
 * The game ends when you cannot make any more moves, ie. the stones are in consecutive positions.
 * <p>
 * When the game ends, what is the minimum and maximum number of moves that you could have made?  Return the answer as an length 2 array: answer = [minimum_moves, maximum_moves]
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: a = 1, b = 2, c = 5
 * Output: [1,2]
 * Explanation: Move the stone from 5 to 3, or move the stone from 5 to 4 to 3.
 * <p>
 * Example 2:
 * <p>
 * Input: a = 4, b = 3, c = 2
 * Output: [0,0]
 * Explanation: We cannot make any moves.
 * <p>
 * Example 3:
 * <p>
 * Input: a = 3, b = 5, c = 1
 * Output: [1,2]
 * Explanation: Move the stone from 1 to 4; or move the stone from 1 to 2 to 4.
 * <p>
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= a <= 100
 * 1 <= b <= 100
 * 1 <= c <= 100
 * a != b, b != c, c != a
 */
/*
Explanation of the problem:
Min moves can only be either 1 or 2. These are the only 2 possible cases 1) AB---C or 2) A--B----C, dashes are spaces. In case 1, A and B are next to each other so you only need 1 move to move C next to B. In case 2, you need to move A next to B and then move C next to B, 2 moves. There is also case 3) A---BC but this is the same as case #1.

So the answer is take the shortest distance between AB and BC. If the distance is 0, you know either A is next to B or B is next to C. So the min is just 1. Is the shortest distance is > 0, you know there are some spaces between A--B and B--C so you need 2 moves.
 */
public class MovingStonesUntilConsecutive {
    public int[] numMovesStones(int a, int b, int c) {
        int[] s = {a, b, c};
        Arrays.sort(s);
        int[] ans = {0, 0};
        int maxDist = s[2] - s[0] - 2;
        if (maxDist == 0) {
            return new int[]{0, 0};
        }
        int minDist = Math.min(s[1] - s[0], s[2] - s[1]) <= 2 ? 1 : 2;
        return new int[]{minDist, maxDist};
    }
}
