package com.vaani.leetcode.contests.biweekly._101;

import java.sql.Array;
import java.util.ArrayList;
import java.util.*;

public class MakeKSubarraySumsEqual {

    public long gcd(long a, long b) {
        if (b==0) return a;
        return gcd(b,a%b);
    }

    public long makeSubKSumEqual(int[] arr, int k) {
        int n = arr.length;
        if(gcd(n, k) == 1) {
            return minCost(arr, (int) n);
        }
        long ans = 0;
        for(int i = 0; i < gcd(n, k); i++) {
            List<Integer> list = new ArrayList<>();
            for(int j = i; j < arr.length; j+=gcd(n, k)) {
                list.add(arr[j]);
            }
//            ans += minCost(list);
        }
        return ans;
    }

    long minCost(int[] A, int n) {
        long cost = 0;
//        A.sort();
        long K = A[n/2];
        for(int num: A) {
            cost += Math.abs(num - K);
        }

        if (n%2 == 0) {
            long temp = 0;
            K = A[n/2 - 1];
            for(int num: A) {
                temp += Math.abs(num - K);
            }
            cost= Math.min(cost, temp);
        }
        return cost;
    }
}
