package com.vaani.leetcode.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/check-array-formation-through-concatenation/
 * 1640. Check Array Formation Through Concatenation
 * Easy
 * <p>
 * You are given an array of distinct integers arr and an array of integer arrays pieces, where the integers in pieces are distinct. Your goal is to form arr by concatenating the arrays in pieces in any order. However, you are not allowed to reorder the integers in each array pieces[i].
 * <p>
 * Return true if it is possible to form the array arr from pieces. Otherwise, return false.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [85], pieces = [[85]]
 * Output: true
 * Example 2:
 * <p>
 * Input: arr = [15,88], pieces = [[88],[15]]
 * Output: true
 * Explanation: Concatenate [15] then [88]
 * Example 3:
 * <p>
 * Input: arr = [49,18,16], pieces = [[16,18,49]]
 * Output: false
 * Explanation: Even though the numbers match, we cannot reorder pieces[0].
 * Example 4:
 * <p>
 * Input: arr = [91,4,64,78], pieces = [[78],[4,64],[91]]
 * Output: true
 * Explanation: Concatenate [91] then [4,64] then [78]
 * Example 5:
 * <p>
 * Input: arr = [1,3,5,7], pieces = [[2,4,6,8]]
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= pieces.length <= arr.length <= 100
 * sum(pieces[i].length) == arr.length
 * 1 <= pieces[i].length <= arr.length
 * 1 <= arr[i], pieces[i][j] <= 100
 * The integers in arr are distinct.
 * The integers in pieces are distinct (i.e., If we flatten pieces in a 1D array, all the integers in this array are distinct).
 */
public class CheckArrayFormationThroughConcatenation {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, int[]> map = Arrays.stream(pieces).collect(Collectors.toMap(x -> x[0], x -> x));

        int i = 0;
        while (i < arr.length) {
            if (!map.containsKey(arr[i])) {
                return false;
            }

            int[] piece = map.get(arr[i]);

            for (int pieceItem : piece) {
                if (arr[i] != pieceItem) {
                    return false;
                } else {
                    i++;
                }
            }
        }
        return true;
    }
}
