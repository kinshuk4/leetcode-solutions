package com.vaani.leetcode.array;

/**
 * https://leetcode.com/problems/h-index/
 * 274. H-Index
 * Medium
 * Given an array of citations (each citation is a non-negative integer) of a researcher,
 * write a function to compute the researcher's h-index.
 *
 * <p>According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her
 * N papers have at least h citations each, and the other N − h papers have no more than h citations
 * each."
 *
 * <p>For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in
 * total and each of them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher
 * has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations
 * each, his h-index is 3.
 *
 * <p>Note: If there are several possible values for h, the maximum one is taken as the h-index.
 *
 */
public class HIndex {

    public static void main(String[] args) throws Exception {
        int[] A = {3, 0, 6, 1, 5};
        System.out.println(new HIndex.Solution1().hIndex(A));
    }
    static class Solution1 {
        /**
         * <p>Solution O(n) Replace all the citations which are greater than n with n, the result will not
         * change with this operation. Maintain a count array with count of each citations. Sum up all the
         * counts from n -> 0 and store this in a array S. Value in array index Si is number of papers
         * having citations at least i.
         *
         * <p>The first value at index i, from right to left in array S which has i <= Si is the answer.
         */
        public int hIndex(int[] citations) {
            int n = citations.length;
            int[] count = new int[n + 1];
            int[] S = new int[n + 1];
            for (int i = 0; i < citations.length; i++) {
                if (citations[i] > n) {
                    citations[i] = n;
                }
            }
            for (int citation : citations) {
                count[citation]++;
            }
            S[n] = count[n];
            for (int i = n - 1; i >= 0; i--) {
                S[i] = count[i] + S[i + 1];
            }
            for (int i = n; i >= 0; i--) {
                if (i <= S[i]) {
                    return i;
                }
            }
            return 0;
        }
    }

    static class UsingBucketSort {
        public int hIndex(int[] citations) {
            int n = citations.length;
            int[] buckets = new int[n+1];
            for(int c : citations) {
                if(c >= n) {
                    buckets[n]++;
                } else {
                    buckets[c]++;
                }
            }
            int count = 0;
            for(int i = n; i >= 0; i--) {
                count += buckets[i];
                if(count >= i) {
                    return i;
                }
            }
            return 0;
        }
    }

}
