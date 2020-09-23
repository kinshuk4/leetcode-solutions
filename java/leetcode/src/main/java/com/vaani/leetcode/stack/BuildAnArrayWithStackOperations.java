package com.vaani.leetcode.stack;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/build-an-array-with-stack-operations/
 * 1441. Build an Array With Stack Operations
 * Easy
 * <p>
 * Given an array target and an integer n. In each iteration, you will read a number from  list = {1,2,3..., n}.
 * <p>
 * Build the target array using the following operations:
 * <p>
 * Push: Read a new element from the beginning list, and push it in the array.
 * Pop: delete the last element of the array.
 * If the target array is already built, stop reading more elements.
 * <p>
 * You are guaranteed that the target array is strictly increasing, only containing numbers between 1 to n inclusive.
 * <p>
 * Return the operations to build the target array.
 * <p>
 * You are guaranteed that the answer is unique.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: target = [1,3], n = 3
 * Output: ["Push","Push","Pop","Push"]
 * Explanation:
 * Read number 1 and automatically push in the array -> [1]
 * Read number 2 and automatically push in the array then Pop it -> [1]
 * Read number 3 and automatically push in the array -> [1,3]
 * <p>
 * Example 2:
 * <p>
 * Input: target = [1,2,3], n = 3
 * Output: ["Push","Push","Push"]
 * <p>
 * Example 3:
 * <p>
 * Input: target = [1,2], n = 4
 * Output: ["Push","Push"]
 * Explanation: You only need to read the first 2 numbers and stop.
 * <p>
 * Example 4:
 * <p>
 * Input: target = [2,3,4], n = 4
 * Output: ["Push","Pop","Push","Push","Push"]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= target.length <= 100
 * 1 <= target[i] <= 100
 * 1 <= n <= 100
 * target is strictly increasing.
 */
public class BuildAnArrayWithStackOperations {
    public List<String> buildArray(int[] target, int n) {
        List<String> ans = new LinkedList<>();

        int j = 0;
        for (int i = 0; i < n && j < target.length; i++) {
            if (target[j] == i + 1) {
                ans.add("Push");
                j++;
            } else {
                ans.addAll(List.of("Push", "Pop"));
            }
        }

        return ans;
    }
}
