package com.vaani.leetcode.design;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/map-sum-pairs/
 * 677. Map Sum Pairs
 * Medium
 * <p>
 * Implement a MapSum class with insert, and sum methods.
 * <p>
 * For the method insert, you'll be given a pair of (string, integer). The string represents the key and the integer represents the value. If the key already existed, then the original key-value pair will be overridden to the new one.
 * <p>
 * For the method sum, you'll be given a string representing the prefix, and you need to return the sum of all the pairs' value whose key starts with the prefix.
 * <p>
 * Example 1:
 * <p>
 * Input: insert("apple", 3), Output: Null
 * Input: sum("ap"), Output: 3
 * Input: insert("app", 2), Output: Null
 * Input: sum("ap"), Output: 5
 */
public class MapSumPairs {
    static class MapSum {
        static class TrieNode {
            Map<Character, TrieNode> children = new HashMap();
            int score;
        }

        private HashMap<String, Integer> map;
        private TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public MapSum() {
            map = new HashMap<>();
            root = new TrieNode();
        }

        public void insert(String key, int val) {
            // we have to add the value to score only when new key is added,
            // if same key is added, we have to replace the value
            // hence using delta
            int delta = val - map.getOrDefault(key, 0);
            map.put(key, val);
            TrieNode curr = root;
            curr.score += delta;
            for (char c : key.toCharArray()) {
                curr.children.putIfAbsent(c, new TrieNode());
                curr = curr.children.get(c);
                curr.score += delta;
            }
        }

        public int sum(String prefix) {
            TrieNode curr = root;
            for (char c : prefix.toCharArray()) {
                curr = curr.children.get(c);
                if (curr == null) return 0;
            }
            return curr.score;
        }
    }

}
