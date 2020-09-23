package com.vaani.leetcode.rand;

import java.util.Random;

/**
 * https://leetcode.com/problems/implement-rand10-using-rand7/
 * 470. Implement Rand10() Using Rand7()
 * Medium
 * <p>
 * 501
 * <p>
 * 169
 * <p>
 * Add to List
 * <p>
 * Share
 * Given a function rand7 which generates a uniform random integer in the range 1 to 7, write a function rand10 which generates a uniform random integer in the range 1 to 10.
 * <p>
 * Do NOT use system's Math.random().
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: 1
 * Output: [7]
 * Example 2:
 * <p>
 * Input: 2
 * Output: [8,4]
 * Example 3:
 * <p>
 * Input: 3
 * Output: [8,1,10]
 * <p>
 * <p>
 * Note:
 * <p>
 * rand7 is predefined.
 * Each testcase has one argument: n, the number of times that rand10 is called.
 */
public class ImplementRand10UsingRand7 {
    static class SolBase {
        Random random = new Random(0);

        public int rand7() {
            return random.nextInt(6) + 1;
        }
    }

    static class Solution extends SolBase {
        public int rand10() {
            int rand40 = 40;
            while (rand40 >= 40) {
                rand40 = (rand7() - 1) * 7 + rand7() - 1;
            }

            return rand40 % 10 + 1;
        }
    }
}
