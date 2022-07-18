package com.vaani.leetcode.greedy;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/construct-target-array-with-multiple-sums/
 * 1354. Construct Target Array With Multiple Sums
 * Hard
 * <p>
 * Given an array of integers target. From a starting array, A consisting of all 1's, you may perform the following procedure :
 * <p>
 * let x be the sum of all elements currently in your array.
 * choose index i, such that 0 <= i < target.size and set the value of A at index i to x.
 * You may repeat this procedure as many times as needed.
 * Return True if it is possible to construct the target array from A otherwise return False.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: target = [9,3,5]
 * Output: true
 * Explanation: Start with [1, 1, 1]
 * [1, 1, 1], sum = 3 choose index 1
 * [1, 3, 1], sum = 5 choose index 2
 * [1, 3, 5], sum = 9 choose index 0
 * [9, 3, 5] Done
 * Example 2:
 * <p>
 * Input: target = [1,1,1,2]
 * Output: false
 * Explanation: Impossible to create target array from [1,1,1,1].
 * Example 3:
 * <p>
 * Input: target = [8,5]
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * N == target.length
 * 1 <= target.length <= 5 * 10^4
 * 1 <= target[i] <= 10^9
 */
public class ConstructTargetArrayWithMultipleSums {
    public boolean isPossible(int[] target) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> (b - a));
        long sum = 0;
        for (int num : target) {
            sum += num;
            maxHeap.add(num);
        }
        while (!maxHeap.isEmpty()) {
            int num = maxHeap.poll();
            sum -= num;
            if (num == 1 || sum == 1) {
                return true;
            }
            if (num < sum || sum == 0 || num % sum == 0) {
                return false;
            }
            num %= sum;
            sum += num;
            maxHeap.add(num);
        }
        return true;

    }
}
