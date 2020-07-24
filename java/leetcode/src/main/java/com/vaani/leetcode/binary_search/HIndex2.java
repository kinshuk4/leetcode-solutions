package com.vaani.leetcode.binary_search;

/**
 * https://leetcode.com/problems/h-index-ii/
 *
 * <p>Follow up for H-Index: What if the citations array is sorted in ascending order? Could you
 * optimize your algorithm?
 */
public class HIndex2 {
    public static void main(String[] args) throws Exception {
        int[] A = {1, 1, 1, 1, 1, 15, 20};
        System.out.println(new HIndex2().hIndex(A));
    }

    public int hIndex(int[] citations) {
        if (citations.length == 0) {
            return 0;
        }
        int N = citations.length;
        int start = 0, end = N;
        int result = -1;

        while (start < end) {
//            int mid = start + (end - start) >>> 1; - this doesnt work for some reason
            int mid = start + (end - start) /2;
            int N_h = N - mid;
            if (citations[mid] > N_h) {
                if (result < N_h) {
                    result = N_h;
                }
                end = mid;
            } else {
                if (result < citations[mid]) {
                    result = citations[mid];
                }
                start = mid + 1;
            }
        }
        return result;
    }
}
