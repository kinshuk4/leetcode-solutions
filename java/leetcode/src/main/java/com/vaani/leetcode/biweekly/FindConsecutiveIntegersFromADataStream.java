package com.vaani.leetcode.biweekly;

import java.util.*;

public class FindConsecutiveIntegersFromADataStream {
    static class DataStream {
        private final int value, k;
        private int size = 0;
        private int windowProblem = 0;
        public DataStream(int value, int k) {
            this.value = value;
            this.k = k;
        }

        public boolean consec(int num) {
            if (num == value) {
                size++;
                if (size >= k) {
                    return true;
                }
            }else {
                size = 0;
            }
            return false;
        }
    }
}
