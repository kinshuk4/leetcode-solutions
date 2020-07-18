package com.vaani.leetcode.design;

import java.util.Arrays;
import java.util.Random;

public class ShuffleAnArray {
    private final int[] original;
    private int[] shuffle = null;
    final Random rand;

    public ShuffleAnArray(int[] nums) {
        this.original = nums;
        shuffle = Arrays.copyOf(nums, nums.length);
        rand = new Random();
    }

    public int[] reset() {
        shuffle = Arrays.copyOf(original, original.length);
        return shuffle;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        for (int i = 0; i < shuffle.length; i++) {
            int x = rand.nextInt(shuffle.length - i);
            int idx = x + i;

            int tmp = shuffle[idx];
            shuffle[idx] = shuffle[i];
            shuffle[i] = tmp;
        }

        return shuffle;
    }
}
