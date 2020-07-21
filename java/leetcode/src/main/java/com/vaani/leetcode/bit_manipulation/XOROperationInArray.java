package com.vaani.leetcode.bit_manipulation;

public class XOROperationInArray {
    public int xorOperation(int n, int start) {
        int res = start;
        for (int i = 1; i < n; i++) {
            res = res ^ (start + 2 * i);
        }
        return res;
    }

    public int xorOperation2(int n, int start) {
        int res = start;
        for (int i = start+2; i < start + 2 * n; i += 2) {
            res = res ^ i;
        }
        return res;
    }
}
