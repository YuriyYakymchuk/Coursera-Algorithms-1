package com.algorithms.part.one.week.fifth.trees;

public class RedBlackTree<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        private final Key key;
        private Value value;
        Node left;
        Node right;
        boolean color;

        public Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    public Value get(Key key) {
        if (key == null) {
            return null;
        }
        Node x = root;
        while (x != null) {
            int comp = key.compareTo(x.key);
            if (comp > 0) {
                x = x.right;
            } else if (comp < 0) {
                x = x.left;
            } else {
                return x.value;
            }
        }
        return null;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node h, Key key, Value value) {
        if (key == null) {
            return null;
        }
        if (h == null) {
            return new Node(key, value, RED);
        }
        int comp = key.compareTo(h.key);
        if (comp > 0) {
            h.right = put(h.right, key, value);
        } else if (comp < 0) {
            h.left = put(h.left, key, value);
        } else {
            h.value = value;
        }
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if(isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        return h;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return  node.color == RED;
    }
}
