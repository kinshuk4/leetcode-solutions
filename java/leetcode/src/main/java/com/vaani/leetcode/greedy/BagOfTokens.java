package com.vaani.leetcode.greedy;

import java.util.*;

/** https://leetcode.com/problems/bag-of-tokens/
 * You have an initial power P, an initial score of 0 points, and a bag of tokens.
 * Each token can be used at most once, has a value token[i], and has potentially two ways to use it.
 *     - If we have at least token[i] power, we may play the token face up, losing token[i] power, and gaining 1 point.
 *     - If we have at least 1 point, we may play the token face down, gaining token[i] power, and losing 1 point.
 * Return the largest number of points we can have after playing any number of tokens.
 *
 * Example 1:
 *
 * Input: tokens = [100], P = 50
 * Output: 0
 * Explanation - we dont have any power to buy tokens, and no points either. So, 0
 * Example 2:
 * Input: tokens = [100,200], P = 150
 * Output: 1
 * Explanation - we have 150 power, so we buy token with value 100. Then we have 1 point. No need to trade now as we have max point already
 *
 * Example 3:
 *
 * Input: tokens = [100,200,300,400], P = 200
 * Output: 2
 * Explanation - we have 200 power, so we buy token with value 100, remaining power = 100. Then we have 1 point.
 * Now we trade point to get token of value 400.  So, we have 500 power to buy 2 tokens - 200, and 300.
 *
 */
public class BagOfTokens {
    public int bagOfTokensScore(int[] tokens, int P) {
        // array may not be sort
        Arrays.sort(tokens);
        int maxPoints = 0;
        int points = 0;

        int i = 0;
        int j = tokens.length-1;

        // i <= j and not i < j as have to consume the last most token
        while (i <= j){
            if(P >= tokens[i]){
                points++;
                P -= tokens[i++]; // i++ as we have used the point
                maxPoints = Math.max(maxPoints, points);
            }else if (points>0){
                // now i will trade my point
                points--;
                P+=tokens[j--];
            }else{
                // nothing left to play
                return maxPoints;
            }

        }
        return maxPoints;

    }
}
