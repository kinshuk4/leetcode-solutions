package com.vaani.leetcode.array;

import java.util.*;

/**
 * https://leetcode.com/problems/rectangle-area-ii/
 * 850. Rectangle Area II
 * Hard
 * <p>
 * We are given a list of (axis-aligned) rectangles. Each rectangle[i] = [xi1, yi1, xi2, yi2] ,
 * where (xi1, yi1) are the coordinates of the bottom-left corner, and (xi2, yi2) are the coordinates of the top-right corner of the ith rectangle.
 * <p>
 * Find the total area covered by all rectangles in the plane. Since the answer may be too large, return it modulo 10^9 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: rectangles = [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
 * Output: 6
 * Explanation: As illustrated in the picture.
 * Example 2:
 * <p>
 * Input: rectangles = [[0,0,1000000000,1000000000]]
 * Output: 49
 * Explanation: The answer is 10^18 modulo (10^9 + 7), which is (10^9)^2 = (-7)^2 = 49.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= rectangles.length <= 200
 * rectanges[i].length = 4
 * 0 <= rectangles[i][j] <= 10^9
 * The total area covered by all rectangles will never exceed 2^63 - 1 and thus will fit in a 64-bit signed integer.
 */
public class RectangleAreaII {

    public static final int MOD = 1_000_000_007;
    public static final int OPEN = 0;
    public static final int CLOSE = 1;

    public int rectangleArea(int[][] rectangles) {
        int[][] events = new int[rectangles.length * 2][];
        int i = 0;
        for (int[] rect : rectangles) {
            events[i++] = new int[]{rect[1], OPEN, rect[0], rect[2]};
            events[i++] = new int[]{rect[3], CLOSE, rect[0], rect[2]};
        }

        Arrays.sort(events, Comparator.comparingInt(a -> a[0]));

        List<int[]> active = new ArrayList<>();
        int yOld = events[0][0];
        long ans = 0;

        for (int[] event : events) {
            int yCurr = event[0], typ = event[1], x1 = event[2], x2 = event[3];

            long width = 0;
            int cur = -1;
            for (int[] xs : active) {
                cur = Math.max(cur, xs[0]);
                width += Math.max(xs[1] - cur, 0);
                cur = Math.max(cur, xs[1]);
            }

            ans += width * (yCurr - yOld);

            if (typ == OPEN) {
                active.add(new int[]{x1, x2});
                active.sort(Comparator.comparingInt(a -> a[0]));
            } else {
                for (i = 0; i < active.size(); i++) {
                    if (active.get(i)[0] == x1 && active.get(i)[1] == x2) {
                        active.remove(i);
                        break;
                    }
                }
            }

            yOld = yCurr;
            System.out.println();
        }

        return (int) (ans % MOD);

    }
}
