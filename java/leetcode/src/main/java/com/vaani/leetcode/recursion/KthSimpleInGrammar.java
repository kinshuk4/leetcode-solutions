package com.vaani.leetcode.recursion;

/**
 * On the first row, we write a 0. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
 * <p>
 * Given row N and index K, return the K-th indexed symbol in row N. (The values of K are 1-indexed.) (1 indexed).
 * <p>
 * Examples:
 * Input: N = 1, K = 1
 * Output: 0
 * <p>
 * Input: N = 2, K = 1
 * Output: 0
 * <p>
 * Input: N = 2, K = 2
 * Output: 1
 * <p>
 * Input: N = 4, K = 5
 * Output: 1
 * <p>
 * Explanation:
 * row 1: 0
 * row 2: 01
 * row 3: 0110
 * row 4: 01101001
 * <p>
 * Note:
 * <p>
 * N will be an integer in the range [1, 30].
 * K will be an integer in the range [1, 2^(N-1)].
 */
public class KthSimpleInGrammar {
    public static void main(String[] args) {
        kthGrammarBoolArray(4, 1);
        System.out.println(kthGrammarBoolArray(30, 434991989));
    }

    // memory limit exceeded
    public int kthGrammarRecursive(int N, int K) {
        String s = kthGrammarHelper(N);
        return s.charAt(K - 1) - '0';
    }

    private String kthGrammarHelper(int n) {
        if (n == 1) {
            return "0";
        }

        String got = kthGrammarHelper(n - 1);
        StringBuilder sb = new StringBuilder();
        for (char c : got.toCharArray()) {
            if (c == '0') {
                sb.append("01");
            } else {
                sb.append("10");
            }
        }

        return sb.toString();
    }


    // memory limit exceeded at 30
    public static int kthGrammarMemo(int N, int K) {
        StringBuilder[] cache = new StringBuilder[N + 1];

        cache[1] = new StringBuilder().append("0");

        for (int i = 2; i <= N; i++) {

            StringBuilder sb = new StringBuilder();

            StringBuilder got = cache[i - 1];
            for (int j = 0; j < got.length(); j++) {
                char c = got.charAt(j);
                if (c == '0') {
                    sb.append("01");
                } else {
                    sb.append("10");
                }
            }

            cache[i] = sb;
        }

        String item = cache[N].toString();
        return item.charAt(K - 1) - '0';
    }

    // row 1: 0
    //row 2: 01
    //row 3: 0110
    //row 4: 01101001
    // row 5: 0110100110010110
    // memory limit exceeded
    public static int kthGrammarPattern(int N, int K) {
        StringBuilder cache = new StringBuilder();
        String row1 = "0";
        String row2 = "01";
        if (N == 1) {
            cache = new StringBuilder(row1);
        } else if (N == 2) {
            cache = new StringBuilder(row2);
        } else {
            cache = new StringBuilder(row2);
            for (int i = 3; i <= N; i++) {
                StringBuilder sb = new StringBuilder();
                for (char c : cache.toString().toCharArray()) {
                    switch (c) {
                        case '0':
                            sb.append("1");
                            break;
                        case '1':
                            sb.append("0");
                            break;
                    }
                }
                cache.append(sb.toString());
            }
        }

        return cache.charAt(K - 1) - '0';
    }


    public static int kthGrammarBoolArray(int N, int K) {
        boolean[] nthRow = new boolean[(int) Math.pow(2, N - 1)];
        if (N >= 2) {
            nthRow[1] = true;
        }
        if (N > 2) {
            for (int i = 3; i <= N; i++) {
                int levelItemsAdded = (int) Math.pow(2, i - 2);
                for (int j = 0; j < levelItemsAdded; j++) {

                    nthRow[j + levelItemsAdded] = !nthRow[j];

                }
            }
        }

        return nthRow[K - 1] ? 1 : 0;
    }

    // https://leetcode.com/problems/k-th-symbol-in-grammar/discuss/711528/Java-Easy-Recursion
    public int kthGrammar(int N, int K) {
        if(N == 1 && K == 1) {return 0;}

        int prevLayerSize = (int) Math.pow(2, N - 2);
        if(K <= prevLayerSize) {
            return kthGrammar(N - 1, K);
        } else {
            return kthGrammar(N - 1, K - prevLayerSize) ^ 1;
        }
    }


}
