package com.vaani.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 354. Russian Doll Envelopes
 * Hard
 * <p>
 * 1766
 * <p>
 * 53
 * <p>
 * Add to List
 * <p>
 * Share
 * You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the height of an envelope.
 * <p>
 * One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
 * <p>
 * Return the maximum number of envelopes can you Russian doll (i.e., put one inside the other).
 * <p>
 * Note: You cannot rotate an envelope.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * Output: 3
 * Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 * Example 2:
 * <p>
 * Input: envelopes = [[1,1],[1,1],[1,1]]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= envelopes.length <= 5000
 * envelopes[i].length == 2
 * 1 <= wi, hi <= 104
 */
public class RussianDollEnvelopes {
    public static void main(String[] args) {
        int[][] A = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        System.out.println(new RussianDollEnvelopes().maxEnvelopes(A));
    }

    /**
     * * <p>Solution: O(N ^ 2) Sort the envelopes based on increasing order of area and for each envelope
     * * iterate through all the possible envelopes which are smaller than that the current envelope and
     * * check the maximum possible envelopes which an be russian dolled.
     */
    static class Envelope {
        int l, b;

        Envelope(int l, int b) {
            this.l = l;
            this.b = b;
        }
    }

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }
        List<Envelope> list = new ArrayList<>();
        for (int[] row : envelopes) {
            list.add(new Envelope(row[0], row[1]));
        }
        list.sort(((o1, o2) -> Integer.compare(o2.l * o2.b, o1.l * o1.b)));
        int[] DP = new int[envelopes.length];
        Arrays.fill(DP, 1);
        for (int i = list.size() - 1; i >= 0; i--) {
            Envelope env = list.get(i);
            for (int j = i + 1, l = list.size(); j < l; j++) {
                Envelope childEnv = list.get(j);
                if (env.l > childEnv.l && env.b > childEnv.b) {
                    DP[i] = Math.max(DP[i], DP[j] + 1);
                }
            }
        }
        return Arrays.stream(DP).max().getAsInt();
    }
}
