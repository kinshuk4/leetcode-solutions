package com.vaani.leetcode.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/largest-divisible-subset/
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.
 * If there are multiple solutions, return any subset is fine.
 * Example 1:
 * nums: [1,2,3]
 * Output: [1,2] (of course, [1,3] will also be ok)
 * Example 2:
 * nums: [1,2,4,8]
 * Output: [1,2,4,8]
 */


public class LargestDivisibleSubset {

	// DP similar to
	// len[i] store the number of largest subset ending with i
	// index[i] trace the last element index for constructing the list result
	public List<Integer> largestDivisibleSubset(int[] nums) {
		LinkedList<Integer> res = new LinkedList<>();
		if (nums == null || nums.length == 0)
			return res;
		Arrays.sort(nums);

		int[] index = new int[nums.length];
		int[] len = new int[nums.length];
		Arrays.fill(len, 1); // we can also fill the arrays later in the loop, index[i] = 1, but for clarity over speed
		Arrays.fill(index, -1);

		for (int i = 1; i < nums.length; i++) {
			int maxLen = 0;
			int id = -1;
			for (int j = i - 1; j >= 0; j--) {
				if (nums[i] % nums[j] == 0) {
					if (len[j] > maxLen) {
						id = j;
						maxLen = len[j];
					}
				}
			}
			len[i] = maxLen + 1;
			index[i] = id;
		}
		int max = 0;
		int point = 0;
		for (int i = 1; i < nums.length; i++) {
			if (len[i] > max) {
				max = len[i];
				point = i;
			}
		}
		while (point != -1) {
			res.add(0, nums[point]);
			point = index[point];
		}
		return res;
	}

	// similar to above - Follow LIS DP solution
	// https://www.youtube.com/watch?v=Wv6DlL0Sawg
	// a%b == 0 or b%a == 0 is the constraint. Hence for given sorted array:
	// 1,2,3,6,7 => consider a = 6, b = 2 => a%b == 0, now b++ b = 3, a%b == 0 is again true
	// If array is not sorted, we have to do( a%b || b%a) check. Sorting reduces the check to only 1 check
	// eg. a%b == 0 when a > b.
	// The idea to use pre[] to track the subset
	// submitted
	public List<Integer> largestDivisibleSubset2(int[] nums) {
		int n = nums.length;
		int[] count = new int[n];
		int[] pre = new int[n];
		Arrays.sort(nums);
		int max = 0, index = -1;
		for (int i = 0; i < n; i++) {
			count[i] = 1;
			pre[i] = -1;
			for (int j = i - 1; j >= 0; j--) {
				if (nums[i] % nums[j] == 0) {
					if (1 + count[j] > count[i]) {
						count[i] = count[j] + 1;
						pre[i] = j;
					}
				}
			}
			if (count[i] > max) {
				max = count[i];
				index = i;
			}
		}
		List<Integer> result = new ArrayList<>();
		while (index != -1) {
			result.add(nums[index]);
			index = pre[index];
		}
		return result;
	}
}
