package com.vaani.leetcode.dp;

/**
 * 1510. Stone Game IV
 * Hard
 * <p>
 * Alice and Bob take turns playing a game, with Alice starting first.
 * <p>
 * Initially, there are n stones in a pile.  On each player's turn, that player makes a move consisting of removing any non-zero square number of stones in the pile.
 * <p>
 * Also, if a player cannot make a move, he/she loses the game.
 * <p>
 * Given a positive integer n. Return True if and only if Alice wins the game otherwise return False, assuming both players play optimally.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 1
 * Output: true
 * Explanation: Alice can remove 1 stone winning the game because Bob doesn't have any moves.
 * Example 2:
 * <p>
 * Input: n = 2
 * Output: false
 * Explanation: Alice can only remove 1 stone, after that Bob removes the last one winning the game (2 -> 1 -> 0).
 * Example 3:
 * <p>
 * Input: n = 4
 * Output: true
 * Explanation: n is already a perfect square, Alice can win with one move, removing 4 stones (4 -> 0).
 * Example 4:
 * <p>
 * Input: n = 7
 * Output: false
 * Explanation: Alice can't win the game if Bob plays optimally.
 * If Alice starts removing 4 stones, Bob will remove 1 stone then Alice should remove only 1 stone and finally Bob removes the last one (7 -> 3 -> 2 -> 1 -> 0).
 * If Alice starts removing 1 stone, Bob will remove 4 stones then Alice only can remove 1 stone and finally Bob removes the last one (7 -> 6 -> 2 -> 1 -> 0).
 * Example 5:
 * <p>
 * Input: n = 17
 * Output: false
 * Explanation: Alice can't win the game if Bob plays optimally.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 10^5
 */
public class StoneGame4 {
    static class UsingMemoizationRecursion {
        public boolean winnerSquareGame(int n) {
            Boolean[] memo = new Boolean[n + 1];
            memo[0] = false; // 0 mean player lost
            return helper(n, memo);
        }

        // Returns true if another player definitely lose => current player wins
        boolean helper(int n, Boolean[] memo) {
            if (memo[n] != null) {
                return memo[n];
            }
            for (int i = (int) Math.sqrt(n); i > 0; i--) {
                if (!helper(n - (i * i), memo)) {
                    return memo[n] = true;
                }
            }
            // this player cannot win
            return memo[n] = false;
        }
    }

}
