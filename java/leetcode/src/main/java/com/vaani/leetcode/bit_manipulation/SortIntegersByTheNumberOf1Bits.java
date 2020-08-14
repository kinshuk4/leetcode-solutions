package com.vaani.leetcode.bit_manipulation;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/sort-integers-by-the-number-of-1-bits/
 * 1356. Sort Integers by The Number of 1 Bits
 * Easy
 * <p>
 * Given an integer array arr. You have to sort the integers in the array in ascending order by the number of 1's in their binary representation and in case of two or more integers have the same number of 1's you have to sort them in ascending order.
 * <p>
 * Return the sorted array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [0,1,2,3,4,5,6,7,8]
 * Output: [0,1,2,4,8,3,5,6,7]
 * Explantion: [0] is the only integer with 0 bits.
 * [1,2,4,8] all have 1 bit.
 * [3,5,6] have 2 bits.
 * [7] has 3 bits.
 * The sorted array by bits is [0,1,2,4,8,3,5,6,7]
 * <p>
 * Example 2:
 * <p>
 * Input: arr = [1024,512,256,128,64,32,16,8,4,2,1]
 * Output: [1,2,4,8,16,32,64,128,256,512,1024]
 * Explantion: All integers have 1 bit in the binary representation, you should just sort them in ascending order.
 * <p>
 * Example 3:
 * <p>
 * Input: arr = [10000,10000]
 * Output: [10000,10000]
 * <p>
 * Example 4:
 * <p>
 * Input: arr = [2,3,5,7,11,13,17,19]
 * Output: [2,3,5,17,7,11,13,19]
 * <p>
 * Example 5:
 * <p>
 * Input: arr = [10,100,1000,10000]
 * Output: [10,100,10000,1000]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 500
 * 0 <= arr[i] <= 10^4
 */
public class SortIntegersByTheNumberOf1Bits {
    public int[] sortByBits(int[] arr) {
        //Convert the given array to  Integer[] from int[], since for custom comparator we need Wrapper classs
        Integer[] boxedArray = Arrays.stream(arr).boxed().toArray(Integer[]::new);

        //Sort based on bitCount, if bit count equal sort by value
        Arrays.sort(boxedArray, (a, b) ->
                Integer.bitCount(a) == Integer.bitCount(b) ? a.compareTo(b) : Integer.bitCount(a) - Integer.bitCount(b));

        //Convert it back to primitive array
        return Arrays.stream(boxedArray).mapToInt(Integer::intValue).toArray();

    }

    public int[] sortByBits2(int[] arr) {
        return Arrays.stream(arr).boxed().
                sorted((a, b) -> Integer.bitCount(a) == Integer.bitCount(b)
                        ? Integer.compare(a, b) : Integer.bitCount(a) - Integer.bitCount(b))
                .mapToInt(Integer::intValue).toArray();
    }
}
