package com.vaani.leetcode.design;

/**
 * https://leetcode.com/problems/design-linked-list/
 * 707. Design Linked List
 * Medium
 * Design your implementation of the linked list. You can choose to use the singly linked list or the doubly linked list. A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node. If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.
 * <p>
 * Implement these functions in your linked list class:
 * <p>
 * get(index) : Get the value of the index-th node in the linked list. If the index is invalid, return -1.
 * addAtHead(val) : Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
 * addAtTail(val) : Append a node of value val to the last element of the linked list.
 * addAtIndex(index, val) : Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
 * deleteAtIndex(index) : Delete the index-th node in the linked list, if the index is valid.
 * Example:
 * <p>
 * MyLinkedList linkedList = new MyLinkedList();
 * linkedList.addAtHead(1);
 * linkedList.addAtTail(3);
 * linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
 * linkedList.get(1);            // returns 2
 * linkedList.deleteAtIndex(1);  // now the linked list is 1->3
 * linkedList.get(1);            // returns 3
 */
public class DesignLinkedList {
    // submitted
    static class UsingDLLWithouTail {
        static class MyLinkedList {
            //instance variables of MyLinkedList class
            private int size; //length of the linked list
            private Node head;  //head node of the linked list

            static class Node {
                //instance variables of Node class
                int val;
                Node next;
                Node prev;

                //Constructor
                Node(int x) {
                    this.val = x;
                }
            }

            /**
             * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
             */
            public int get(int index) {
                if (index >= this.size) {
                    return -1;
                }
                Node curr = this.head;
                int i = 0;
                while (i != index) {
                    i++;
                    curr = curr.next;
                }
                return curr.val;
            }

            /**
             * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
             */
            public void addAtHead(int val) {
                Node newHead = new Node(val);
                newHead.next = this.head;
                newHead.prev = null;
                this.head = newHead;
                this.size++;
            }

            /**
             * Append a node of value val to the last element of the linked list.
             */
            public void addAtTail(int val) {
                if (this.size == 0) {
                    addAtHead(val);
                    return;
                }
                Node newTail = new Node(val);
                newTail.next = null;
                Node cur = this.head;
                while (cur.next != null) {
                    cur = cur.next;
                }
                newTail.prev = cur;
                cur.next = newTail;
                this.size++;
            }

            /**
             * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
             */
            public void addAtIndex(int index, int val) {
                if (index < 0 || index > this.size) {
                    return;
                }
                if (index == 0) {
                    addAtHead(val);
                    return;
                }
                if (index == this.size) {
                    addAtTail(val);
                    return;
                }
                Node node = new Node(val);
                Node cur = this.head;
                int counter = 0;
                while (counter < index - 1) {
                    counter++;
                    cur = cur.next;
                }
                Node pre = cur;
                Node next = cur.next;
                node.next = next;
                node.prev = pre;
                pre.next = node;
                if (next != null)
                    next.prev = node;
                this.size++;
            }

            /**
             * Delete the index-th node in the linked list, if the index is valid.
             */
            public void deleteAtIndex(int index) {
                if (index < 0 || index >= this.size) {
                    return;
                }
                Node cur = this.head;
                if (index == 0) {
                    this.head = cur.next;
                } else {
                    int counter = 0;
                    while (counter < index - 1) {
                        counter++;
                        cur = cur.next;
                    }
                    Node pre = cur;
                    Node next = cur.next.next;
                    pre.next = next;
                    if (next != null) next.prev = pre;
                }
                this.size--;
            }
        }
    }

    // submitted
    static class UsingDLLWithTail {
        static class MyLinkedList {
            //instance variables of MyLinkedList class
            private int size; //length of the linked list
            private Node head;  //head node of the linked list
            private Node tail;  //tail node of the linked list

            static class Node {
                //instance variables of Node class
                int val;
                Node next;
                Node prev;

                //Constructor
                Node(int x) {
                    this.val = x;
                }
            }

            /**
             * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
             */
            public int get(int index) {
                if (index >= this.size) {
                    return -1;
                }
                Node curr = this.head;
                int i = 0;
                while (i != index) {
                    i++;
                    curr = curr.next;
                }
                return curr.val;
            }

            /**
             * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
             */
            public void addAtHead(int val) {
                Node newHead = new Node(val);

                newHead.next = this.head;
                if (this.size != 0) {
                    this.head.prev = newHead;
                } else {
                    tail = newHead;
                }

                this.head = newHead;
                this.size++;
            }

            /**
             * Append a node of value val to the last element of the linked list.
             */
            public void addAtTail(int val) {
                if (this.size == 0) {
                    addAtHead(val);
                    return;
                }
                Node newTail = new Node(val);
                newTail.prev = this.tail;
                this.tail.next = newTail;
                this.tail = newTail;
                this.size++;
            }

            /**
             * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
             */
            public void addAtIndex(int index, int val) {
                if (index < 0 || index > this.size)
                    return;
                if (index == 0) {
                    addAtHead(val);
                    return;
                }
                if (index == this.size) {
                    addAtTail(val);
                    return;
                }

                Node newNode = new Node(val);

                Node curr = this.head;
                int counter = 0;
                // index is 0 based, but we want to get pointer to nth link
                // not counter < index, but counter < index-1
                while (counter < index - 1) {
                    counter++;
                    curr = curr.next;
                }

                Node prev = curr;
                Node next = curr.next;
                newNode.next = next;
                newNode.prev = prev;
                prev.next = newNode;
                if (next != null) {
                    next.prev = newNode;
                }
                this.size++;
            }

            /**
             * Delete the index-th node in the linked list, if the index is valid.
             */
            public void deleteAtIndex(int index) {
                if (index < 0 || index >= this.size) {
                    return;
                }

                if (this.size == 1) {
                    this.head = null;
                    this.tail = null;
                    return;
                }

                if (index == 0) {
                    this.head = this.head.next;
                } else if (index == this.size - 1) {
                    this.tail = this.tail.prev;
                } else {
                    int counter = 0;
                    Node curr = this.head;
                    while (counter < index - 1) {
                        counter++;
                        curr = curr.next;
                    }
                    Node prev = curr;
                    Node next = curr.next.next;
                    prev.next = next;
                    if (next != null) {
                        next.prev = prev;
                    }
                }
                this.size--;
            }

            @Override
            public String toString() {
                Node curr = this.head;
                StringBuilder sb = new StringBuilder();
                while (curr != null) {
                    sb.append(curr.val).append("->");
                    curr = curr.next;
                }
                return sb.toString();
            }
        }
    }


    public static void main(String[] args) {
        UsingDLLWithTail.MyLinkedList linkedList = new UsingDLLWithTail.MyLinkedList(); // Initialize empty LinkedList
        linkedList.addAtHead(1);
        System.out.println(linkedList);
        linkedList.addAtTail(3);
        System.out.println(linkedList);
        linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
        System.out.println(linkedList);
        System.out.println(linkedList.get(1));            // returns 2
        System.out.println(linkedList);
        linkedList.deleteAtIndex(1);  // now the linked list is 1->3
        System.out.println(linkedList);
        System.out.println(linkedList.get(1));            // returns 3
        System.out.println(linkedList);

    }
}
