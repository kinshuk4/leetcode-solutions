package com.vaani.leetcode.contests.biweekly._98;

import java.util.*;

public class HandlingSumQueriesAfterUpdate {
    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        List<Long> ansList = new ArrayList<>();
        long sum = 0;
        for (var q : queries) {
            switch (q[0]) {
                case 1:
                    for (int i = q[1]; i <= q[2]; i++) {
                        nums1[i] = 1 - nums1[i];
                    }
                    break;
                case 2:
                    sum = 0;
                    for(int i = 0 ; i < nums1.length; i++) {
                        nums2[i] += nums1[i] * q[1];
                        sum += nums2[i];
                    }
                    break;
                case 3:
                    if (sum == 0) {
                        for (int i = 0; i < nums2.length; i++) {
                            sum += nums2[i];
                        }
                    }
                    ansList.add(sum);
                    break;
            }
        }
        long[] ans = new long[ansList.size()];
        int i = 0;
        for (long v: ansList) {
            ans[i++] = v;
        }
        return ans;
    }
}
