package com.vaani.leetcode.string;

import java.util.*;

public class PalindromicPairs {
    static class UsingTrie {
        static class TrieNode {
            TrieNode[] children;
            int index;
            List<Integer> list;

            TrieNode() {
                children = new TrieNode[26];
                index = -1;
                list = new ArrayList<>();
            }
        }

        public List<List<Integer>> palindromePairs(String[] words) {
            List<List<Integer>> ans = new ArrayList<>();

            TrieNode root = new TrieNode();

            for (int i = 0; i < words.length; i++) {
                addWord(root, words[i], i);
            }

            for (int i = 0; i < words.length; i++) {
                search(root, words, i, ans);
            }

            return ans;
        }

        private void addWord(TrieNode root, String word, int index) {
            for (int i = word.length() - 1; i >= 0; i--) {
                int j = word.charAt(i) - 'a';

                if (root.children[j] == null) {
                    root.children[j] = new TrieNode();
                }

                if (isPalindrome(word, 0, i)) {
                    root.list.add(index);
                }

                root = root.children[j];
            }

            root.list.add(index);
            root.index = index;
        }

        private void search(TrieNode root, String[] words, int i, List<List<Integer>> ans) {
            for (int j = 0; j < words[i].length(); j++) {
                if (root.index >= 0 && root.index != i && isPalindrome(words[i], j, words[i].length() - 1)) {
                    ans.add(Arrays.asList(i, root.index));
                }

                root = root.children[words[i].charAt(j) - 'a'];
                if (root == null) {
                    return;
                }
            }

            for (int j : root.list) {
                if (i == j) {
                    continue;
                }
                ans.add(Arrays.asList(i, j));
            }
        }

        private boolean isPalindrome(String word, int i, int j) {
            while (i < j) {
                if (word.charAt(i++) != word.charAt(j--)) {
                    return false;
                }
            }

            return true;
        }

    }

    static class UsingMap {
        public List<List<Integer>> palindromePairs(String[] words) {
            Map<String, Integer> map = new HashMap<>();
            Set<Integer> set = new TreeSet<>();
            int n = words.length;

            for (int i = 0; i < n; i++) {
                map.put(words[i], i);
                set.add(words[i].length());
            }

            List<List<Integer>> ans = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                int wordLen = words[i].length();

                if (wordLen == 1) {
                    if (map.containsKey("")) {
                        ans.add(List.of(i, map.get("")));
                        ans.add(List.of(map.get(""), i));
                    }
                    continue;
                }
                String reverse = new StringBuilder(words[i]).reverse().toString();
                if (map.containsKey(reverse) && map.get(reverse) != i) {
                    ans.add(List.of(i, map.get(reverse)));
                }

                for (int num : set) {
                    if (num == wordLen) {
                        break;
                    }
                    if (isPalindrome(reverse, 0, wordLen - 1 - num)) {
                        String s1 = reverse.substring(wordLen - num);
                        if (map.containsKey(s1)) {
                            ans.add(Arrays.asList(i, map.get(s1)));
                        }
                    }

                    if (isPalindrome(reverse, num, wordLen - 1)) {
                        String s2 = reverse.substring(0, num);
                        if (map.containsKey(s2)) {
                            ans.add(Arrays.asList(map.get(s2), i));
                        }
                    }
                }
            }
            return ans;
        }

        private boolean isPalindrome(String word, int i, int j) {
            while (i < j) {
                if (word.charAt(i++) != word.charAt(j--)) {
                    return false;
                }
            }

            return true;
        }
    }
}
