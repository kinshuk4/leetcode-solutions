package com.vaani.leetcode.binary_search;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/
 * 1337. The K Weakest Rows in a Matrix
 * Easy
 * <p>
 * Given a m * n matrix mat of ones (representing soldiers) and zeros (representing civilians), return the indexes of the k weakest rows in the matrix ordered from the weakest to the strongest.
 * <p>
 * A row i is weaker than row j, if the number of soldiers in row i is less than the number of soldiers in row j, or they have the same number of soldiers but i is less than j. Soldiers are always stand in the frontier of a row, that is, always ones may appear first and then zeros.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: mat =
 * [[1,1,0,0,0],
 * [1,1,1,1,0],
 * [1,0,0,0,0],
 * [1,1,0,0,0],
 * [1,1,1,1,1]],
 * k = 3
 * Output: [2,0,3]
 * Explanation:
 * The number of soldiers for each row is:
 * row 0 -> 2
 * row 1 -> 4
 * row 2 -> 1
 * row 3 -> 2
 * row 4 -> 5
 * Rows ordered from the weakest to the strongest are [2,0,3,1,4]
 * <p>
 * Example 2:
 * <p>
 * Input: mat =
 * [[1,0,0,0],
 * [1,1,1,1],
 * [1,0,0,0],
 * [1,0,0,0]],
 * k = 2
 * Output: [0,2]
 * Explanation:
 * The number of soldiers for each row is:
 * row 0 -> 1
 * row 1 -> 4
 * row 2 -> 1
 * row 3 -> 1
 * Rows ordered from the weakest to the strongest are [0,2,3,1]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == mat.length
 * n == mat[i].length
 * 2 <= n, m <= 100
 * 1 <= k <= m
 * matrix[i][j] is either 0 or 1.
 */
public class TheKWeakestRowsInAMatrix {
    static class UsingBruteCountAndHeap {
        static class Pair {
            int rowId;
            int solders;

            public Pair(int id, int count) {
                this.rowId = id;
                this.solders = count;
            }
        }

        public int[] kWeakestRows(int[][] mat, int k) {
            PriorityQueue<Pair> pq = new PriorityQueue<>(k, (o1, o2) -> o1.solders == o2.solders ? o1.rowId - o2.rowId : o1.solders - o2.solders);
            int id = 0;
            for (int[] row : mat) {
                int count = 0;
                for (int i = 0; i < row.length; i++) {
                    if (row[i] == 1) {
                        count++;
                    }
                }
                pq.add(new Pair(id, count));
                id++;
            }
            int[] res = new int[k];
            int i = 0;
            while (i < k) {
                res[i++] = pq.poll().rowId;
            }
            return res;
        }
    }

    static class UsingBinarySearch {
        public int[] kWeakestRows(int[][] mat, int k) {
            PriorityQueue<Pair> pq = new PriorityQueue<>(k,
                    (a, b) -> a.solders == b.solders ? b.rowId - a.rowId : b.solders - a.solders);

            for (int i = 0; i < mat.length; i++) {
                pq.offer(new Pair(i, numOnes(mat[i])));
                if (pq.size() > k) {
                    pq.poll();
                }
            }

            int[] ans = new int[k];
            while (k > 0) {
                ans[--k] = pq.poll().rowId;
            }

            return ans;
        }

        static class Pair {
            int rowId;
            int solders;

            public Pair(int id, int count) {
                this.rowId = id;
                this.solders = count;
            }
        }

        private int numOnes(int[] row) {
            int lo = 0;
            int hi = row.length;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                if (row[mid] == 1) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }

            return lo;
        }
    }

}
