package com.vaani.leetcode.array;

public class MonotonicArray {
    static class Simple {
        public boolean isMonotonic(int[] A) {
            return increasing(A) || decreasing(A);
        }

        public boolean increasing(int[] A) {
            for (int i = 0; i < A.length - 1; ++i)
                if (A[i] > A[i + 1])
                    return false;
            return true;
        }

        public boolean decreasing(int[] A) {
            for (int i = 0; i < A.length - 1; ++i)
                if (A[i] < A[i + 1])
                    return false;
            return true;
        }
    }

    static class UsingLogicalOperator {
        public boolean isMonotonic2(int[] A) {
            boolean inc = true, dec = true;
            for (int i = 1; i < A.length; ++i) {
                inc &= A[i - 1] <= A[i];
                dec &= A[i - 1] >= A[i];
            }
            return inc || dec;
        }
    }

    static class UsingIntegerCompar {
        public boolean isMonotonic(int[] A) {
            int store = 0;
            for (int i = 0; i < A.length - 1; ++i) {
                int c = Integer.compare(A[i], A[i + 1]);
                if (c == 0)
                    continue;
                if (store != 0 && c != store)
                    return false;
                store = c;

            }

            return true;
        }
    }

}
