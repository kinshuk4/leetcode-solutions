package com.vaani.leetcode.linked_list;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/split-linked-list-in-parts/
 * 725. Split Linked List in Parts
 * Medium
 * <p>
 * Given the head of a singly linked list and an integer k, split the linked list into k consecutive linked list parts.
 * <p>
 * The length of each part should be as equal as possible: no two parts should have a size differing by more than one. This may lead to some parts being null.
 * <p>
 * The parts should be in the order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal to parts occurring later.
 * <p>
 * Return an array of the k parts.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3], k = 5
 * Output: [[1],[2],[3],[],[]]
 * Explanation:
 * The first element output[0] has output[0].val = 1, output[0].next = null.
 * The last element output[4] is null, but its string representation as a ListNode is [].
 * Example 2:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5,6,7,8,9,10], k = 3
 * Output: [[1,2,3,4],[5,6,7],[8,9,10]]
 * Explanation:
 * The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [0, 1000].
 * 0 <= Node.val <= 1000
 * 1 <= k <= 50
 */
public class SplitLinkedListInParts {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        ListNode[] result = new SplitLinkedListInParts().splitListToParts(root, 5);
        System.out.println(result);
    }

    /**
     * <p>Solution O(N) do a linear scan and split the array by required size.
     */
    public ListNode[] splitListToParts(ListNode head, int k) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int tempK = k;
        int N = list.size();
        List<ListNode> result = new ArrayList<>();
        ListNode curr = new ListNode(-1);
        ListNode prev = curr;
        int i = 0, j = 0;
        int count = 0;
        int P = N / tempK;
        if ((N % tempK) > 0) {
            P++;
        }
        while (j < N) {
            if (j - i < P) {
                prev.next = new ListNode(list.get(j));
                prev = prev.next;
                j++;
                count++;
            } else {
                result.add(curr.next);
                i = j;
                curr = new ListNode(-1);
                prev = curr;
                tempK--;
                P = (N - count) / tempK;
                if (((N - count) % tempK) > 0) {
                    P++;
                }
            }
        }
        result.add(curr.next);
        while (result.size() < k) {
            result.add(null);
        }
        ListNode[] nodes = new ListNode[result.size()];
        for (int l = 0; l < result.size(); l++) {
            nodes[l] = result.get(l);
        }
        return nodes;
    }
}
