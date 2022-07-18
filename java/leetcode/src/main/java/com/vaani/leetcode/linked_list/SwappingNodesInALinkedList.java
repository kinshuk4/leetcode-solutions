package com.vaani.leetcode.linked_list;

import com.vaani.dsa.ds.core.list.sll.simple.ListNode;

/**
 * 1721. Swapping Nodes in a Linked List
 * Medium
 * <p>
 * You are given the head of a linked list, and an integer k.
 * <p>
 * Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [1,4,3,2,5]
 * Example 2:
 * <p>
 * Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
 * Output: [7,9,6,6,8,7,3,0,9,5]
 * Example 3:
 * <p>
 * Input: head = [1], k = 1
 * Output: [1]
 * Example 4:
 * <p>
 * Input: head = [1,2], k = 1
 * Output: [2,1]
 * Example 5:
 * <p>
 * Input: head = [1,2,3], k = 2
 * Output: [1,2,3]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is n.
 * 1 <= k <= n <= 10^5
 * 0 <= Node.val <= 100
 */
public class SwappingNodesInALinkedList {
    public ListNode swapNodes(ListNode head, int k) {
        k = k - 1;

        ListNode a = head;

        while (k > 0) {
            a = a.next;
            k--;
        }

        ListNode nthHead = a;
        ListNode b = head;
        while (nthHead.next != null) {
            b = b.next;
            nthHead = nthHead.next;
        }

        int temp = a.val;
        a.val = b.val;
        b.val = temp;

        return head;
    }
}
