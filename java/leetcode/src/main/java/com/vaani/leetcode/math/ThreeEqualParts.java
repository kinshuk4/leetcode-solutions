package com.vaani.leetcode.math;

/**
 * 927. Three Equal Parts
 * Hard
 * <p>
 * You are given an array arr which consists of only zeros and ones, divide the array into three non-empty parts such that all of these parts represent the same binary value.
 * <p>
 * If it is possible, return any [i, j] with i + 1 < j, such that:
 * <p>
 * arr[0], arr[1], ..., arr[i] is the first part,
 * arr[i + 1], arr[i + 2], ..., arr[j - 1] is the second part, and
 * arr[j], arr[j + 1], ..., arr[arr.length - 1] is the third part.
 * All three parts have equal binary values.
 * If it is not possible, return [-1, -1].
 * <p>
 * Note that the entire part is used when considering what binary value it represents. For example, [1,1,0] represents 6 in decimal, not 3. Also, leading zeros are allowed, so [0,1,1] and [1,1] represent the same value.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [1,0,1,0,1]
 * Output: [0,3]
 * Example 2:
 * <p>
 * Input: arr = [1,1,0,1,1]
 * Output: [-1,-1]
 * Example 3:
 * <p>
 * Input: arr = [1,1,0,0,1]
 * Output: [0,2]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 3 <= arr.length <= 3 * 104
 * arr[i] is 0 or 1
 */
public class ThreeEqualParts {
    public int[] threeEqualParts(int[] arr) {
        int oneCnt = 0, n = arr.length;

        for (int i : arr) {
            oneCnt += i;
        }

        if (oneCnt == 0) {
            return new int[]{0, n - 1};
        }

        if (oneCnt % 3 != 0) {
            return new int[]{-1, -1};
        }

        int low = 0, mid = 0, high = 0;
        int equalOneCnt = oneCnt / 3;
        oneCnt = 0;

        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                continue;
            }

            if (oneCnt == 0) {
                low = i;
            }

            oneCnt++;

            if (oneCnt == equalOneCnt + 1) {
                mid = i;
            }

            if (oneCnt == 2 * equalOneCnt + 1) {
                high = i;
            }

        }

        //skip all elements which are same
        while (high < n && arr[low] == arr[mid] && arr[mid] == arr[high]) {
            low++;
            mid++;
            high++;
        }

        //if we reach end then its possible so return (start-1,end)
        if (high == n) {
            return new int[]{low - 1, mid};
        }

        //else return as its not possible
        return new int[]{-1, -1};

    }
}
