package com.vaani.leetcode.hashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/distribute-candies/
 * 575. Distribute Candies
 * Easy
 * <p>
 * Given an integer array with even length, where different numbers in this array represent different kinds of candies. Each number means one candy of the corresponding kind. You need to distribute these candies equally in number to brother and sister. Return the maximum number of kinds of candies the sister could gain.
 * <p>
 * Example 1:
 * <p>
 * Input: candies = [1,1,2,2,3,3]
 * Output: 3
 * Explanation:
 * There are three different kinds of candies (1, 2 and 3), and two candies for each kind.
 * Optimal distribution: The sister has candies [1,2,3] and the brother has candies [1,2,3], too.
 * The sister has three different kinds of candies.
 * <p>
 * Example 2:
 * <p>
 * Input: candies = [1,1,2,3]
 * Output: 2
 * Explanation: For example, the sister has candies [2,3] and the brother has candies [1,1].
 * The sister has two different kinds of candies, the brother has only one kind of candies.
 *
 * <p>The length of the given array is in range [2, 10,000], and will be even. The number in given
 * array is in range [-100,000, 100,000].
 */
public class DistributeCandies {
    public static void main(String[] args) {
    }

    /**
     * * <p>Solution: O(N) Use a HashSet to identify all the different possible candies. The maximum types
     * * of candies sister can get is always Min(N/2, Number Of Unique Type of Candies)
     */
    public int distributeCandies(int[] candies) {
        Set<Integer> set = Arrays.stream(candies).boxed().collect(Collectors.toSet());
        return Math.min(candies.length / 2, set.size());
    }

    // one liner
    public int distributeCandies2(int[] candies) {
        return Math.min(candies.length / 2, ((int) Arrays.stream(candies).distinct().count()));
    }
}
