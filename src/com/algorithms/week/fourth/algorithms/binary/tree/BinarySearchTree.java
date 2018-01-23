package com.algorithms.week.fourth.algorithms.binary.tree;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {

    private class Node {
        Key key;
        Value value;

        Node left;
        Node right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value);
        }
        int comp = key.compareTo(x.key);
        if (comp < 0) {
            x.left = put(x.left, key, value);
        } else if (comp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        return x;
    }

    public Value get(Key key) {
        if (key == null) {
            return null;
        }
        Node x = root;
        while (x != null) {
            int comp = key.compareTo(x.key);
            if (comp < 0) {
                x = x.left;
            } else if (comp > 0) {
                x = x.right;
            } else {
                return x.value;
            }
        }
        return null;
    }

    public void delete(Key key) {
        root = delete(null, root, key);
    }

    private Node delete(Node parent, Node node, Key key) {
        if (node == null || key == null) {
            return null;
        }
        int comp = key.compareTo(node.key);
        if (comp < 0) {
            delete(node, node.left, key);
        } else if (comp > 0) {
            delete(node, node.right, key);
        } else {
            if (parent == null) {
                return null;
            }
            if (parent.right == node) {
                parent.right = null;
            } else if (parent.left == node) {
                parent.left = null;
            }
        }
        return node;
    }

}
