package com.vaani.leetcode.binary_search;

public class AvlTree {

    private static class Node {
        Node left, right;
        Node parent;
        int value ;
        int height = 0;

        public Node(int data, Node parent) {
            this.value = data;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return value + " height " + height + " parent " + (parent == null ?
                    "NULL" : parent.value) + " | ";
        }

        void setLeftChild(Node child) {
            if (child != null) {
                child.parent = this;
            }

            this.left = child;
        }

        void setRightChild(Node child) {
            if (child != null) {
                child.parent = this;
            }

            this.right = child;
        }
    }

    private Node root = null;

    public void insert(int data) {
        insert(root, data);
    }

    private int height(Node node) {
        return node == null ? -1 : node.height;
    }

    private void insert(Node node, int value) {
        if (root == null) {
            root = new Node(value, null);
            return;
        }

        if (value < node.value) {
            if (node.left != null) {
                insert(node.left, value);
            } else {
                node.left = new Node(value, node);
            }

            if (height(node.left) - height(node.right) == 2) { //left heavier
                if (value < node.left.value) {
                    rotateRight(node);
                } else {
                    rotateLeftThenRight(node);
                }
            }
        } else if (value > node.value) {
            if (node.right != null) {
                insert(node.right, value);
            } else {
                node.right = new Node(value, node);
            }

            if (height(node.right) - height(node.left) == 2) { //right heavier
                if (value > node.right.value)
                    rotateLeft(node);
                else {
                    rotateRightThenLeft(node);
                }
            }
        }

        reHeight(node);
    }

    private void rotateRight(Node pivot) {
        Node parent = pivot.parent;
        Node leftChild = pivot.left;
        Node rightChildOfLeftChild = leftChild.right;
        pivot.setLeftChild(rightChildOfLeftChild);
        leftChild.setRightChild(pivot);
        if (parent == null) {
            this.root = leftChild;
            leftChild.parent = null;
            return;
        }

        if (parent.left == pivot) {
            parent.setLeftChild(leftChild);
        } else {
            parent.setRightChild(leftChild);
        }

        reHeight(pivot);
        reHeight(leftChild);
    }

    private void rotateLeft(Node pivot) {
        Node parent = pivot.parent;
        Node rightChild = pivot.right;
        Node leftChildOfRightChild = rightChild.left;
        pivot.setRightChild(leftChildOfRightChild);
        rightChild.setLeftChild(pivot);
        if (parent == null) {
            this.root = rightChild;
            rightChild.parent = null;
            return;
        }

        if (parent.left == pivot) {
            parent.setLeftChild(rightChild);
        } else {
            parent.setRightChild(rightChild);
        }

        reHeight(pivot);
        reHeight(rightChild);
    }

    private void reHeight(Node node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private void rotateLeftThenRight(Node node) {
        rotateLeft(node.left);
        rotateRight(node);
    }

    private void rotateRightThenLeft(Node node) {
        rotateRight(node.right);
        rotateLeft(node);
    }

    public boolean delete(int key) {
        Node target = search(key);
        if (target == null) return false;
        target = deleteNode(target);
        balanceTree(target.parent);
        return true;
    }

    private Node deleteNode(Node target) {
        if (isLeaf(target)) { //leaf
            if (isLeftChild(target)) {
                target.parent.left = null;
            } else {
                target.parent.right = null;
            }
        } else if (target.left == null ^ target.right == null) { //exact 1 child
            Node nonNullChild = target.left == null ? target.right : target.left;
            if (isLeftChild(target)) {
                target.parent.setLeftChild(nonNullChild);
            } else {
                target.parent.setRightChild(nonNullChild);
            }
        } else {//2 children
            Node immediatePredInOrder = immediatePredInOrder(target);
            target.value = immediatePredInOrder.value;
            target = deleteNode(immediatePredInOrder);
        }

        reHeight(target.parent);
        return target;
    }

    private Node immediatePredInOrder(Node node) {
        Node current = node.left;
        while (current.right != null) {
            current = current.right;
        }

        return current;
    }

    private boolean isLeftChild(Node child) {
        return (child.parent.left == child);
    }

    private boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    private int calDifference(Node node) {
        int rightHeight = height(node.right);
        int leftHeight = height(node.left);
        return rightHeight - leftHeight;
    }

    private void balanceTree(Node node) {
        int difference = calDifference(node);
        Node parent = node.parent;
        if (difference == -2) {
            if (height(node.left.left) >= height(node.left.right)) {
                rotateRight(node);
            } else {
                rotateLeftThenRight(node);
            }
        } else if (difference == 2) {
            if (height(node.right.right) >= height(node.right.left)) {
                rotateLeft(node);
            } else {
                rotateRightThenLeft(node);
            }
        }

        if (parent != null) {
            balanceTree(parent);
        }

        reHeight(node);
    }

    public Node search(int key) {
        return binarySearch(root, key);
    }

    private Node binarySearch(Node node, int key) {
        if (node == null) return null;

        if (key == node.value) {
            return node;
        }

        if (key < node.value && node.left != null) {
            return binarySearch(node.left, key);
        }

        if (key > node.value && node.right != null) {
            return binarySearch(node.right, key);
        }

        return null;
    }

    public void traverseInOrder() {
        System.out.println("ROOT " + root.toString());
        inorder(root);
        System.out.println();
    }

    private void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.toString());
            inorder(node.right);
        }
    }

    public static void main(String[] args) {
        AvlTree avl = new AvlTree();
        avl.insert(1);
        avl.traverseInOrder();
        avl.insert(2);
        avl.traverseInOrder();
        avl.insert(3);
        avl.traverseInOrder();
        avl.insert(4);
        avl.traverseInOrder();
        avl.delete(1);
        avl.traverseInOrder();
        avl.insert(5);
        avl.traverseInOrder();
        avl.insert(6);
        avl.traverseInOrder();
        avl.delete(3);
        avl.traverseInOrder();
        avl.delete(5);
        avl.traverseInOrder();
    }

}
