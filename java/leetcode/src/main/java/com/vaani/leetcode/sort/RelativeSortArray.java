package com.vaani.leetcode.sort;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/relative-sort-array/
 * <p>
 * 1122. Relative Sort Array
 * Easy
 * <p>
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
 * <p>
 * Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.  Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * Output: [2,2,2,1,4,3,3,9,6,7,19]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * Each arr2[i] is distinct.
 * Each arr2[i] is in arr1.
 */
public class RelativeSortArray {
    public static void main(String[] args) {
        //
    }

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Long> counts = Arrays.stream(arr1).boxed().collect(
                Collectors.groupingBy(
                        Function.identity(),
                        HashMap::new, // can be skipped
                        Collectors.counting()
                )
        );

        Set<Integer> set = Arrays.stream(arr2).boxed().collect(Collectors.toSet());

        List<Integer> present = new ArrayList<>();
        for (int num : arr2) {
            long count = counts.get(num);
            for (int i = 0; i < count; i++) {
                present.add(num);
            }
        }

        List<Integer> notPresent = new ArrayList<>();
        for (int k : counts.keySet()) {
            if (!set.contains(k)) {
                long count = counts.get(k);
                for (int i = 0; i < count; i++) {
                    notPresent.add(k);
                }
            }
        }
        notPresent.sort(Comparator.comparingInt(o -> o));

        int n = present.size();
        int m = notPresent.size();

        int[] ans = new int[present.size() + notPresent.size()];

        IntStream.range(0, n).forEach(i -> {
            ans[i] = present.get(i);
        });

        IntStream.range(0, m).forEach(i -> {
            ans[i + n] = notPresent.get(i);
        });

        return ans;
    }
}
