package com.vaani.leetcode.binary_search;

/**
 * https://leetcode.com/problems/guess-number-higher-or-lower/
 * 374. Guess Number Higher or Lower
 * Easy
 * We are playing the Guess Game. The game is as follows:
 * <p>
 * I pick a number from 1 to n. You have to guess which number I picked.
 * <p>
 * Every time you guess wrong, I'll tell you whether the number is higher or lower.
 * <p>
 * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
 * <p>
 * -1 : My number is lower
 * 1 : My number is higher
 * 0 : Congrats! You got it!
 * Example:
 * n = 10, I pick 6.
 * <p>
 * Return 6.
 */

// Binary Search
public class GuessNumberHigherOrLower extends GuessGame {
    public GuessNumberHigherOrLower(int target) {
        this.target = target;
    }

    public int guessNumber(int n) {
        int start = 1;
        int end = n;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (guess(mid) == 0) {
                return mid;
            }
            if (guess(mid) == 1) {
                start = mid + 1;
            } else if (guess(mid) == -1) {
                end = mid - 1;
            }
        }
        return start;
    }

    public int guess(int num) {
        return num < target ? 1 : num == target ? 0 : -1;
    }

    public static void main(String[] args) {
        GuessNumberHigherOrLower guess = new GuessNumberHigherOrLower(10);
        System.out.println(guess.guessNumber(20));
    }
}
