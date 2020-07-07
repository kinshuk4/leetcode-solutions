package com.vaani.leetcode.array;

import org.junit.Assert;

/**
 * https://leetcode.com/problems/can-place-flowers/
 *
 * <p>Suppose you have a long flowerbed in which some of the plots are planted and some are not.
 * However, flowers cannot be planted in adjacent plots - they would compete for water and both
 * would die.
 *
 * <p>Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means
 * not empty), and a number n, return if n new flowers can be planted in it without violating the
 * no-adjacent-flowers rule.
 *
 * <p>Example 1: Input: flowerbed = [1,0,0,0,1], n = 1 Output: True Example 2: Input: flowerbed =
 * [1,0,0,0,1], n = 2 Output: False Note: The input array won't violate no-adjacent-flowers rule.
 * The input array size is in the range of [1, 20000]. n is a non-negative integer which won't
 * exceed the input array size.
 */
public class CanPlaceFlowers {
    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int[] n = {1, 0, 0, 0, 1};
        CanPlaceFlowers underTest = new CanPlaceFlowers();
        Assert.assertTrue(underTest.canPlaceFlowers(n, 1));
        Assert.assertFalse(underTest.canPlaceFlowers(n, 2));

        n = new int[]{1, 0, 0, 0, 0, 1};
        Assert.assertTrue(underTest.canPlaceFlowers(n, 3));
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {

        if (n == 0) {
            return true;
        }
        int len = flowerbed.length;
        if (len == 1) {
            if (flowerbed[0] == 0) {
                if (n <= 1) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        }

        int countOfAvailableSpots = 0;


        for (int i = 0; i < len; ) {
            int spot = flowerbed[i];
            if (spot == 1) {
                i = i + 2; // look for next spot
            } else {
                //if ((i == 0 && flowerbed[i + 1] == 0) || (i == len - 1 && flowerbed[i - 1] == 0) || (flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0))
                if (i == 0) {
                    if (flowerbed[i + 1] == 0) {
                        countOfAvailableSpots++;
                        i = i + 2;
                    } else {
                        i = i + 1;
                    }
                } else if (i == len - 1) {
                    if (flowerbed[i - 1] == 0) {
                        countOfAvailableSpots++;
                        i = i + 2;
                    } else {
                        i = i + 1;
                    }
                } else if (flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
                    countOfAvailableSpots++;
                    i = i + 2;
                } else {
                    i = i + 1;
                }
            }
            if (countOfAvailableSpots >= n) {
                return true;
            }
        }
        return false;
    }

    public boolean canPlaceFlowers2(int[] flowerbed, int n) {

        int[] T = new int[flowerbed.length + 4];
        for (int i = 0, j = 2; i < flowerbed.length; i++) T[j++] = flowerbed[i];
        T[0] = 1;
        T[T.length - 1] = 1;
        int total = 0, count = 0;
        for (int i = 1; i < T.length; i++) {
            if (T[i] == 0) count++;
            else {
                if ((count % 2) == 0) total += ((count / 2) - 1);
                else total += (count / 2);
                count = 0; // reset
            }
        }
        return (total >= n);
    }
}
