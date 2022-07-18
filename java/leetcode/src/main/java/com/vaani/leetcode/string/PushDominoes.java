package com.vaani.leetcode.string;

import java.util.Arrays;

/**
 * 838. Push Dominoes
 * Medium
 * <p>
 * There are n dominoes in a line, and we place each domino vertically upright. In the beginning, we simultaneously push some of the dominoes either to the left or to the right.
 * <p>
 * After each second, each domino that is falling to the left pushes the adjacent domino on the left. Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.
 * <p>
 * When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.
 * <p>
 * For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.
 * <p>
 * You are given a string dominoes representing the initial state where:
 * <p>
 * dominoes[i] = 'L', if the ith domino has been pushed to the left,
 * dominoes[i] = 'R', if the ith domino has been pushed to the right, and
 * dominoes[i] = '.', if the ith domino has not been pushed.
 * Return a string representing the final state.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: dominoes = "RR.L"
 * Output: "RR.L"
 * Explanation: The first domino expends no additional force on the second domino.
 * Example 2:
 * <p>
 * <p>
 * Input: dominoes = ".L.R...LR..L.."
 * Output: "LL.RR.LLRRLL.."
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == dominoes.length
 * 1 <= n <= 105
 * dominoes[i] is either 'L', 'R', or '.'.
 */
public class PushDominoes {
    public static void main(String[] args) {
//        System.out.println(new PushDominoes.UsingCountArray().pushDominoes("RR.L"));
        System.out.println(new PushDominoes.UsingCountArray().pushDominoes("..R..")); // ..RRR
    }

    public String pushDominoes(String dominoes) {
        int R = -1, L = -1;
        char[] A = dominoes.toCharArray();
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 'L') {
                if (R > L) {
                    int d = (i - R);
                    int st;
                    st = R + d / 2;
                    if ((d % 2) == 0) {
                        A[st] = '.';
                    }
                    for (int j = st + 1; j < i; j++) {
                        A[j] = 'L';
                    }
                } else {
                    for (int j = (L == -1 ? 0 : L); j < i; j++) {
                        A[j] = 'L';
                    }
                }
                L = i;
            } else {
                if (A[i] == 'R') {
                    R = i;
                } else {
                    if (R > L) {
                        A[i] = 'R';
                    }
                }
            }
        }
        return String.valueOf(A);
    }

    static class UsingCountArray {
        public String pushDominoes(String dominoes) {
            int n = dominoes.length();
            int[] left = new int[n];
            int[] right = new int[n];
            char prev = '.';
            int count = 0;
            for (int i = 0; i < n; i++) {
                char c = dominoes.charAt(i);
                if (c == 'R') {
                    count = 1;
                    prev = c;
                }else if(c == 'L') {
                    count = 0;
                    prev = c;
                }else if (c == '.' && prev == 'R'){
                    right[i] = count++;
                }
            }

            prev = '.';
            for (int i = n - 1; i >= 0; i--) {
                char c = dominoes.charAt(i);
                if (c == 'L') {
                    count = 1;
                    prev = c;
                }else if(c == 'R') {
                    count = 0;
                    prev = c;
                }else if (c == '.' && prev == 'L'){
                    left[i] = count++;
                }
            }
            System.out.println(Arrays.toString(right));
            System.out.println(Arrays.toString(left));
            char[] ans = new char[n];
            for (int i = 0; i < n; i++) {
                int L = left[i];
                int R = right[i];
                if (L == 0 && R == 0) {
                    ans[i] = dominoes.charAt(i);
                } else if (L == 0) {
                    ans[i] = 'R';
                } else if (R == 0) {
                    ans[i] = 'L';
                } else if (L == R) {
                    ans[i] = '.';
                }else if(L < R) {
                    ans[i] = 'L';
                }else {
                    ans[i] = 'R';
                }
            }
            return new String(ans);
        }
    }
}
