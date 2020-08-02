package com.vaani.leetcode.string;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/next-closest-time/
 * Medium
 * Problem:
 * Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.
 * <p>
 * You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.
 * <p>
 * Example 1:
 * <p>
 * Input: "19:34"
 * Output: "19:39"
 * Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours and 59 minutes later.
 * <p>
 * Example 2:
 * <p>
 * Input: "23:59"
 * Output: "22:22"
 * Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assume
 * Companies: Google
 */
public class NextClosestTime {
    public String nextClosestTime1(String time) {
        int start = 60 * Integer.parseInt(time.substring(0, 2));
        start += Integer.parseInt(time.substring(3));
        int ans = start;
        int elapsed = 24 * 60;
        Set<Integer> allowed = new HashSet();
        for (char c : time.toCharArray()) {
            if (c != ':') {
                allowed.add(c - '0');
            }
        }

        for (int h1 : allowed) {
            for (int h2 : allowed) {
                if (h1 * 10 + h2 < 24) {
                    for (int m1 : allowed) {
                        for (int m2 : allowed) {
                            if (m1 * 10 + m2 < 60) {
                                int cur = 60 * (h1 * 10 + h2) + (m1 * 10 + m2);
                                int candElapsed = Math.floorMod(cur - start, 24 * 60);
                                if (0 < candElapsed && candElapsed < elapsed) {
                                    ans = cur;
                                    elapsed = candElapsed;
                                }
                            }
                        }
                    }
                }
            }
        }

        return String.format("%02d:%02d", ans / 60, ans % 60);
    }

    // https://www.youtube.com/watch?v=99Gw7Hezii8
    public String nextClosestTime(String time) {
        // get current time in minutes
        int minutes = 60 * Integer.parseInt(time.substring(0, 2));
        minutes += Integer.parseInt(time.substring(3));
        Set<Integer> allowed = new HashSet<>();
        for (char c : time.toCharArray()) {
            if (c != ':') {
                allowed.add(c - '0');
            }
        }

        while (true) {
            minutes = (minutes + 1) % (24 * 60);
            int[] digits = new int[]{minutes / 60 / 10, minutes / 60 % 10, minutes % 60 / 10, minutes % 60 % 10};
            if (isNotValid(allowed, digits)) {
                continue;
            }
            return String.format("%02d:%02d", minutes / 60, minutes % 60);
        }
    }

    private boolean isNotValid(Set<Integer> allowed, int[] digits) {
        for (int d : digits) {
            if (!allowed.contains(d)) {
                return true;
            }
        }
        return false;
    }
}
