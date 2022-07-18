package com.vaani.leetcode.string;


import java.util.ArrayList;
import java.util.*;

/**
 * https://leetcode.com/problems/search-suggestions-system/
 * 1268. Search Suggestions System
 * Medium
 * <p>
 * <p>
 * Given an array of strings products and a string searchWord.
 * We want to design a system that suggests at most three product names from products after each character of searchWord is typed.
 * Suggested products should have common prefix with the searchWord.
 * If there are more than three products with a common prefix return the three lexicographically minimums products.
 * <p>
 * Return list of lists of the suggested products after each character of searchWord is typed.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * Output: [
 * ["mobile","moneypot","monitor"],
 * ["mobile","moneypot","monitor"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"]
 * ]
 * Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
 * After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
 * After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
 * Example 2:
 * <p>
 * Input: products = ["havana"], searchWord = "havana"
 * Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 * Example 3:
 * <p>
 * Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
 * Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
 * Example 4:
 * <p>
 * Input: products = ["havana"], searchWord = "tatiana"
 * Output: [[],[],[],[],[],[],[]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= products.length <= 1000
 * There are no repeated elements in products.
 * 1 <= Σ products[i].length <= 2 * 10^4
 * All characters of products[i] are lower-case English letters.
 * 1 <= searchWord.length <= 1000
 * All characters of searchWord are lower-case English letters.
 */
public class SearchSuggestionsSystem {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        TrieNodeLexicographic root = new TrieNodeLexicographic();
        for (String product : products) {
            insert(root, product);
        }
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < searchWord.length(); i++) {
            String prefix = searchWord.substring(0, i + 1);
            List<String> searchResult = wordsWithPrefix(root, prefix);
            ans.add(searchResult);
        }
        return ans;

    }

    // Uses treemap
    static class TrieNodeLexicographic {
        Map<Character, TrieNodeLexicographic> children;
        boolean isWord = false;

        TrieNodeLexicographic() {
            children = new TreeMap<>();
        }
    }


    private static void insert(TrieNodeLexicographic root, String word) {
        TrieNodeLexicographic current = root;
        for (int i = 0; i < word.length(); i++) {
            char curr = word.charAt(i);
            current.children.putIfAbsent(curr, new TrieNodeLexicographic());
            current = current.children.get(curr);
        }
        current.isWord = true;
    }

    public List<String> wordsWithPrefix(TrieNodeLexicographic root, String prefix) {
        List<String> searchRes = new ArrayList<>();
        wordsWithPrefixHelper("", prefix, root, searchRes);
        return searchRes;
    }

    private static void wordsWithPrefixHelper(String prefix, String word, TrieNodeLexicographic node, List<String> searchResult) {
        TrieNodeLexicographic current = node;
        if (current == null || searchResult.size() == 3) {
            return;
        }
        for (int i = 0; i < word.length(); i++) {
            char curr = word.charAt(i);
            if (!current.children.containsKey(curr)) {
                return;
            }
            current = current.children.get(curr);
        }
        if (current != null) {
            if (current.isWord && searchResult.size() < 3) {
                searchResult.add(prefix + word);
            }
            for (Character c : current.children.keySet()) {
                List<String> temp = new ArrayList<>();
                wordsWithPrefixHelper(prefix + word, String.valueOf(c), current, temp);
                if (!temp.isEmpty()) {
                    int i = 0;
                    while (searchResult.size() < 3 && i < temp.size()) {
                        searchResult.add(temp.get(i++));
                    }
                }
            }
        }
    }


}
