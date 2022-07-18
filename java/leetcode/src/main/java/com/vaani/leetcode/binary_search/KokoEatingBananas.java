package com.vaani.leetcode.binary_search;

/**
 * https://leetcode.com/problems/koko-eating-bananas/
 * 875. Koko Eating Bananas
 * Medium
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
 * <p>
 * Koko can decide her bananas-per-hour eating speed of k.
 * Each hour, she chooses some pile of bananas and eats k bananas from that pile.
 * If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
 * <p>
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 * <p>
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 * Example 2:
 * <p>
 * Input: piles = [30,11,23,4,20], h = 5
 * Output: 30
 * Example 3:
 * <p>
 * Input: piles = [30,11,23,4,20], h = 6
 * Output: 23
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= piles.length <= 10^4
 * piles.length <= h <= 10^9
 * 1 <= piles[i] <= 10^9
 *
 * <p>Solution: O(N x log Max(piles[i])) Binary search for the minimum possible value between (1 and
 * max(piles[i]))
 */
public class KokoEatingBananas {
    public static void main(String[] args) {
        int[] A = {312884470};
        System.out.println(new KokoEatingBananas().minEatingSpeed(A, 968709470));
    }

    public int minEatingSpeed(int[] piles, int h) {
        int max = 0;
        for (int i = 0; i < piles.length; i++) {
            max = Math.max(max, piles[i]);
        }
        if (h == piles.length) {
            return max;
        }
        int hi = max, lo = 1;
        int answer = h;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            boolean status = check(piles, h, mid);
            if (status) {
                answer = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return answer;
    }

    private boolean check(int[] piles, int h, int k) {
        for (int p : piles) {
            if (p <= k) {
                h--;
            } else {
                int q = p / k;
                if ((p % k) > 0) {
                    q++;
                }
                h -= q;
            }
            if (h < 0) {
                return false;
            }
        }
        return true;
    }
}
