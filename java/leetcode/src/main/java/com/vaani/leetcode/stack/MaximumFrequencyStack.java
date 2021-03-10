package com.vaani.leetcode.stack;

import java.util.*;

/**
 * 895. Maximum Frequency Stack
 * Hard
 * Implement FreqStack, a class which simulates the operation of a stack-like data structure.
 * <p>
 * FreqStack has two functions:
 * <p>
 * push(int x), which pushes an integer x onto the stack.
 * pop(), which removes and returns the most frequent element in the stack.
 * If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input:
 * ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
 * [[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
 * Output: [null,null,null,null,null,null,null,5,7,5,4]
 * Explanation:
 * After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:
 * <p>
 * pop() -> returns 5, as 5 is the most frequent.
 * The stack becomes [5,7,5,7,4].
 * <p>
 * pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
 * The stack becomes [5,7,5,4].
 * <p>
 * pop() -> returns 5.
 * The stack becomes [5,7,4].
 * <p>
 * pop() -> returns 4.
 * The stack becomes [5,7].
 * <p>
 * <p>
 * Note:
 * <p>
 * Calls to FreqStack.push(int x) will be such that 0 <= x <= 10^9.
 * It is guaranteed that FreqStack.pop() won't be called if the stack has zero elements.
 * The total number of FreqStack.push calls will not exceed 10000 in a single test case.
 * The total number of FreqStack.pop calls will not exceed 10000 in a single test case.
 * The total number of FreqStack.push and FreqStack.pop calls will not exceed 150000 across all test cases.
 */
public class MaximumFrequencyStack {
    static class FreqStack {
        private final Map<Integer, Integer> freqMap;
        private final PriorityQueue<Node> pq;
        private int time = 0;

        static class Node {
            int val;
            int frequency;
            int time;

            public Node(int val, int frequency, int time) {
                this.val = val;
                this.frequency = frequency;
                this.time = time;
            }
        }

        public FreqStack() {
            freqMap = new HashMap<>();
            pq = new PriorityQueue<>((Node a, Node b) -> a.frequency == b.frequency ? b.time - a.time : b.frequency - a.frequency);
        }

        public void push(int x) {
            freqMap.put(x, freqMap.getOrDefault(x, 0) + 1);
            Node n = new Node(x, freqMap.get(x), time++);
            pq.add(n);
        }

        public int pop() {
            Node node = pq.poll();
            freqMap.put(node.val, freqMap.get(node.val) - 1);
            return node.val;
        }
    }

    /**
     * Your FreqStack object will be instantiated and called as such:
     * FreqStack obj = new FreqStack();
     * obj.push(x);
     * int param_2 = obj.pop();
     */
}
