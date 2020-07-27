package com.vaani.leetcode.heap;

import java.util.*;

/** https://leetcode.com/problems/top-k-frequent-words/
 * Given a non-empty list of words, return the k most frequent elements.
 *
 * <p>Your answer should be sorted by frequency from highest to lowest. If two words have the same
 * frequency, then the word with the lower alphabetical order comes first.
 *
 * <p>Example 1: Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2 Output: ["i",
 * "love"] Explanation: "i" and "love" are the two most frequent words. Note that "i" comes before
 * "love" due to a lower alphabetical order. Example 2: Input: ["the", "day", "is", "sunny", "the",
 * "the", "the", "sunny", "is", "is"], k = 4 Output: ["the", "is", "sunny", "day"] Explanation:
 * "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence
 * being 4, 3, 2 and 1 respectively. Note: You may assume k is always valid, 1 ≤ k ≤ number of
 * unique elements. Input words contain only lowercase letters. Follow up: Try to solve it in O(n
 * log k) time and O(n) extra space.
 *
 * <p>Solution: O(n log k). Calculate frequency and maintain a inverse priority queue of size k and
 * add elements. Return result by reversing the priority queue elements.
 */
public class TopKFrequentWords {

    public static void main(String[] args) throws Exception {
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        List<String> sorted = new TopKFrequentWords().topKFrequent(words, 2);
        sorted.stream().forEach(System.out::println);
    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
        Queue<Pair> minHeap = new PriorityQueue<>((a, b) -> (a.freq == b.freq) ? b.word.compareTo(a.word) : Integer.compare(a.freq, b.freq));

        for (String w : map.keySet()) {
            int f = map.get(w);
            minHeap.offer(new Pair(w, f));
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        LinkedList<String> result = new LinkedList<>(); // nOt using arraylist as it doesnt provide addFirst, otherwise we have to do collection reverse

        while (!minHeap.isEmpty()) {
            result.addFirst(minHeap.poll().word);
        }
        return result;
    }

    static class Pair {
        String word;
        int freq;

        Pair(String word, int freq) {
            this.word = word;
            this.freq = freq;
        }
    }
}
