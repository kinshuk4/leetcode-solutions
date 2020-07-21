package com.vaani.leetcode.dp;

import static com.vaani.dsa.algo.paradigm.dp.ClimbingStairs.climbStairsDPO1;

/**
 * https://leetcode.com/problems/climbing-stairs/
 * You are climbing a stair case. It takes n steps to
 * reach to the top.
 *
 * <p>Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the
 * top?
 *
 * <p>Note: Given n will be a positive integer.
 */
public class ClimbingStairs {
    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
    }

    public int climbStairs(int n) {
        return climbStairsDPO1(n);
    }
}
