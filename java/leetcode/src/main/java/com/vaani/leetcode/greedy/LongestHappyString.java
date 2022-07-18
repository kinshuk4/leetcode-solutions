package com.vaani.leetcode.greedy;

import java.util.*;

/**
 * https://leetcode.com/problems/longest-happy-string/
 */
public class LongestHappyString {
    static class Pair {
        char ch;
        int count;

        public Pair(Character ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }

    static public String longestDiverseString(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((x, y) -> Integer.compare(y.count, x.count));

        if (a > 0) maxHeap.add(new Pair('a', a));
        if (b > 0) maxHeap.add(new Pair('b', b));
        if (c > 0) maxHeap.add(new Pair('c', c));

        while (maxHeap.size() > 1) {

            Pair one = maxHeap.poll();
            if (one.count >= 2) {
                sb.append(one.ch).append(one.ch);
                one.count -= 2;
            } else {
                sb.append(one.ch);
                one.count -= 1;
            }

            Pair two = maxHeap.poll();
            if (two.count >= 2 && one.count < two.count) {
                sb.append(two.ch).append(two.ch);
                two.count -= 2;
            } else {
                sb.append(two.ch);
                two.count -= 1;
            }

            if (one.count > 0) maxHeap.add(one);
            if (two.count > 0) maxHeap.add(two);
        }

        if (!maxHeap.isEmpty()) {
            if (sb.charAt(sb.length() - 1) != maxHeap.peek().ch) {
                if (maxHeap.peek().count >= 2) {
                    sb.append(maxHeap.peek().ch);
                    sb.append(maxHeap.peek().ch);
                } else {
                    sb.append(maxHeap.peek().ch);
                }
            }
        }
        return sb.toString();
    }


}
