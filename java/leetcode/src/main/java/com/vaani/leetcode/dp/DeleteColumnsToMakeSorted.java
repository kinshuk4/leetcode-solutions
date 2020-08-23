package com.vaani.leetcode.dp;

/**
 *
 * 944. Delete Columns to Make Sorted
 * Easy
 *
 * We are given an array A of N lowercase letter strings, all of the same length.
 *
 * Now, we may choose any set of deletion indices, and for each string,
 * we delete all the characters in those indices.
 *
 * For example, if we have an array A = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3},
 * then the final array after deletions is ["bef", "vyz"], and
 * the remaining columns of A are ["b","v"], ["e","y"], and ["f","z"].
 * (Formally, the c-th column is [A[0][c], A[1][c], ..., A[A.length-1][c]]).
 *
 * Suppose we chose a set of deletion indices D such that after deletions, each remaining column in A is in non-decreasing sorted order.
 *
 * Return the minimum possible value of D.length.
 *
 *
 * Example 1:
 *
 * Input: A = ["cba","daf","ghi"]
 * Output: 1
 * Explanation:
 * After choosing D = {1}, each column ["c","d","g"] and ["a","f","i"] are in non-decreasing sorted order.
 * If we chose D = {}, then a column ["b","a","h"] would not be in non-decreasing sorted order.
 *
 * Example 2:
 *
 * Input: A = ["a","b"]
 * Output: 0
 * Explanation: D = {}
 *
 * Example 3:
 *
 * Input: A = ["zyx","wvu","tsr"]
 * Output: 3
 * Explanation: D = {0, 1, 2}
 *
 *
 * Constraints:
 *
 *     1 <= A.length <= 100
 *     1 <= A[i].length <= 1000
 *
 */

/*
 * <p>For clarity, A[0] is in lexicographic order (ie. A[0][0] <= A[0][1] <= ... <= A[0][A[0].length
 * - 1]), A[1] is in lexicographic order (ie. A[1][0] <= A[1][1] <= ... <= A[1][A[1].length - 1]),
 * and so on.
 *
 * * <p>Example 1:
 *
 * <p>Input: ["babca","bbazb"] Output: 3 Explanation: After deleting columns 0, 1, and 4, the final
 * array is A = ["bc", "az"]. Both these rows are individually in lexicographic order (ie. A[0][0]
 * <= A[0][1] and A[1][0] <= A[1][1]). Note that A[0] > A[1] - the array A isn't necessarily in
 * lexicographic order. Example 2:
 *
 * <p>Input: ["edcba"] Output: 4 Explanation: If we delete less than 4 columns, the only row won't
 * be lexicographically sorted. Example 3:
 *
 * <p>Input: ["ghi","def","abc"] Output: 0 Explanation: All rows are already lexicographically
 * sorted.
 *
 */
public class DeleteColumnsToMakeSorted {
    public static void main(String[] args) {
        String[] A = {"ghi", "def", "abc"};
        System.out.println(new DeleteColumnsToMakeSorted().minDeletionSize(A));
    }

    int[] DP;

    public int minDeletionSize(String[] A) {
        DP = new int[A[0].length()];
        int max = 0;
        for (int i = 0; i < A[0].length(); i++) {
            max = Math.max(max, dp(A, i));
        }
        return A[0].length() - max;
    }

    private int dp(String[] A, int i) {
        if (i >= A[0].length()) return 0;
        else if (DP[i] != 0) return DP[i];
        DP[i] = 1;
        for (int j = i + 1; j < A[0].length(); j++) {
            boolean possible = true;
            for (String str : A) {
                if (str.charAt(j) < str.charAt(i)) {
                    possible = false;
                    break;
                }
            }
            if (possible) {
                DP[i] = Math.max(DP[i], dp(A, j) + 1);
            }
        }
        return DP[i];
    }

    public int minDeletionSize2(String[] A) {
        int ans = 0;
        for (int i = 0; i < A[0].length(); i++) {
            for (int j = 1; j < A.length; j++) {
                if (A[j-1].charAt(i) > A[j].charAt(i)) {
                    ans++;
                    break;
                }
            }
        }

        return ans;
    }
}
