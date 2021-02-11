package com.vaani.leetcode.dp;

/**
 * 312. Burst Balloons
 * Hard
 * <p>
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 * <p>
 * Find the maximum coins you can collect by bursting the balloons wisely.
 * <p>
 * Note:
 * <p>
 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * Example:
 * <p>
 * Input: [3,1,5,8]
 * Output: 167
 * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 * coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
public class BurstBalloons {


    public static void main(String[] args) throws Exception {
        int[] A = {3, 1, 5, 8};
        System.out.println(new BurstBalloons.SolutionDP().maxCoins(A));
    }

    static class SolutionDP {
        /**
         * <p>Solution: O(N ^ 3) The recursive top-down dp memorization solution is based on the idea where
         * each balloon is considered as the last balloon to be burst. For the above example 1,3,1,5,8,1 (1
         * included at either end to indicate boundary) each balloon starting from 3 until 8 is chosen each
         * time as a the last balloon to be burst using the boundary 1 on either side. So, for the first
         * iteration the result is calculated as 3*1(left boundary)*1(right boundary) + dp(1, 3)
         * (left-sub-problem having 1 and 3 as the boundary) + dp(3, 1) (right-sub-problem having 3 and 1 as
         * the boundary)
         */

        private int[][] dp;

        public int maxCoins(int[] nums) {
            int[] balloons = new int[nums.length + 2];
            balloons[0] = balloons[balloons.length - 1] = 1; // boundary
            // copy nums array in balloons from position 1
            System.arraycopy(nums, 0, balloons, 1, nums.length);
            int[][] dp = new int[balloons.length][balloons.length];
            for (int r = 2; r < balloons.length; r++) {
                for (int i = 0; i < balloons.length; i++) {
                    int j = i + r;
                    if (j < balloons.length) {
                        int max = Integer.MIN_VALUE;
                        for (int t = i + 1; t < j; t++) {
                            max = Math.max(max, balloons[t] * balloons[i] * balloons[j] + dp[t][j] + dp[i][t]);
                        }
                        dp[i][j] = max;
                    }
                }
            }
            return dp[0][balloons.length - 1];
    /*    for (int i = 0; i < nums.length; i++) {
      N[i + 1] = nums[i];
    }
    dp = new int[N.length][N.length];
    for (int[] aDp : dp) {
      Arrays.fill(aDp, -1);
    }*/
            //    return dp(0, N.length - 1);
        }

//        private int dpf(int l, int r) {
//            if (l + 1 == r) return 0;
//            if (dp[l][r] != -1) return dp[l][r];
//            int result = 0;
//            for (int i = l + 1; i < r; i++) {
//                result = Math.max(result, balloons[i] * balloons[l] * balloons[r] + dpf(l, i) + dpf(i, r));
//            }
//            dp[l][r] = result;
//            return dp[l][r];
//        }
    }


}
