package com.algorithms.part.one.week.fifth.trees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class KdTree {

    private static final boolean VERTICAL = true;

    private static final boolean HORIZONTAL = false;

    private Node root;
    private Point2D nearestPoint;
    private int size;

    private class Node {
        private Point2D value;
        private Node right;
        private Node left;
        private boolean division;

        public Node(Point2D value) {
            this.value = value;
            right = null;
            left = null;
            division = false;
        }

        public void setValue(Point2D value) {
            this.value = value;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setDivision(boolean division) {
            this.division = division;
        }

        public Point2D getValue() {
            return value;
        }

        public Node getRight() {
            return right;
        }

        public Node getLeft() {
            return left;
        }

        public boolean isDivision() {
            return division;
        }
    }

    public KdTree() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size() <= 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D point) {
        validatePoint(point);
        Node newNode = new Node(point);
        Node currNode = root;
        if (currNode == null) {
            newNode.division = VERTICAL;
            root = newNode;
            size++;
            return;
        }
        while (true) {
            if (point.equals(currNode.value)) {
                return;
            }
            if (currNode.division == VERTICAL) {
                if (point.x() < currNode.getValue().x()) {
                    if (currNode.left == null) {
                        newNode.division = HORIZONTAL;
                        currNode.left = newNode;
                        size++;
                        return;
                    }
                    else {
                        currNode = currNode.left;
                    }
                }
                else {
                    if (currNode.right == null) {
                        newNode.division = HORIZONTAL;
                        currNode.right = newNode;
                        size++;
                        return;
                    }
                    else {
                        currNode = currNode.right;
                    }
                }
            }
            else {
                if (point.y() < currNode.getValue().y()) {
                    if (currNode.left == null) {
                        newNode.division = VERTICAL;
                        currNode.left = newNode;
                        size++;
                        return;
                    }
                    else {
                        currNode = currNode.left;
                    }
                }
                else {
                    if (currNode.right == null) {
                        newNode.division = VERTICAL;
                        currNode.right = newNode;
                        size++;
                        return;
                    }
                    else {
                        currNode = currNode.right;
                    }

                }
            }
        }
    }

    public boolean contains(Point2D point) {
        validatePoint(point);
        Node currNode = root;
        while (currNode != null) {
            if (point.equals(currNode.value)) {
                return true;
            }
            if (currNode.division == VERTICAL) {
                if (point.x() < currNode.getValue().x()) {
                    currNode = currNode.left;
                }
                else {
                    currNode = currNode.right;
                }
            }
            else {
                if (point.y() < currNode.getValue().y()) {
                    currNode = currNode.left;
                }
                else {
                    currNode = currNode.right;
                }
            }
        }
        return false;
    }

    public void draw() {
        if (root != null) {
            drawNodes(root);
        }
    }

    private void drawNodes(Node node) {
        Point2D point = node.value;
        point.draw();

        if (node.left != null) {
            drawNodes(node.left);
        }

        if (node.right != null) {
            drawNodes(node.right);
        }
    }

    public Iterable<Point2D> range(RectHV rectangle) {
        if (rectangle == null) {
            throw new IllegalArgumentException("Rectangle can not be null.");
        }
        Stack<Point2D> containedPoints = new Stack<>();
        addContainedPoints(root, rectangle, containedPoints);

        return containedPoints;
    }

    private void addContainedPoints(Node node, RectHV rectangle, Stack<Point2D> containedPoints) {
        if (node == null) {
            return;
        }

        if (node.division == VERTICAL) {
            if (node.getValue().x() > rectangle.xmax()) {
                addContainedPoints(node.left, rectangle, containedPoints);
            }
            else if (node.getValue().x() < rectangle.xmin()) {
                addContainedPoints(node.right, rectangle, containedPoints);
            }
            else {
                if (rectangle.contains(node.value)) {
                    containedPoints.push(node.value);
                }
                addContainedPoints(node.left, rectangle, containedPoints);
                addContainedPoints(node.right, rectangle, containedPoints);
            }
        }
        else {
            if (node.getValue().y() > rectangle.ymax()) {
                addContainedPoints(node.left, rectangle, containedPoints);
            }
            else if (node.getValue().y() < rectangle.ymin()) {
                addContainedPoints(node.right, rectangle, containedPoints);
            }
            else {
                if (rectangle.contains(node.value)) {
                    containedPoints.push(node.value);
                }
                addContainedPoints(node.left, rectangle, containedPoints);
                addContainedPoints(node.right, rectangle, containedPoints);
            }
        }
    }

    public Point2D nearest(Point2D point) {
        validatePoint(point);
        if (root == null) {
            return null;
        }
        nearestPoint = root.value;
        findNearest(root, point);
        return nearestPoint;
    }

    private void findNearest(Node node, Point2D point) {
        if (node == null) {
            return;
        }

        if (node.division == VERTICAL) {
            if (point.distanceSquaredTo(node.value) < point.distanceSquaredTo(nearestPoint)) {
                nearestPoint = node.value;
                if (node.value.x() >= point.x()) {
                    findNearest(node.left, point);
                    findNearest(node.right, point);
                }
                else {
                    findNearest(node.right, point);
                    findNearest(node.left, point);
                }
            }
            else {
                if (node.value.x() > point.x()) {
                    findNearest(node.left, point);
                }
                else if (node.value.x() < point.x()) {
                    findNearest(node.right, point);
                }
                else {
                    findNearest(node.left, point);
                    findNearest(node.right, point);
                }
            }

        }
        else {
            if (point.distanceSquaredTo(node.value) < point.distanceSquaredTo(nearestPoint)) {
                nearestPoint = node.value;
                if (node.value.y() >= point.y()) {
                    findNearest(node.left, point);
                    findNearest(node.right, point);
                }
                else {
                    findNearest(node.right, point);
                    findNearest(node.left, point);
                }
            }
            else {
                if (node.value.y() > point.y()) {
                    findNearest(node.left, point);
                }
                else if (node.value.y() < point.y()) {
                    findNearest(node.right, point);
                }
                else {
                    findNearest(node.left, point);
                    findNearest(node.right, point);
                }
            }
        }
    }

    private void validatePoint(Point2D point) {
        if (point == null) {
            throw new IllegalArgumentException("Point can't be null.");
        }
    }

    public static void main(String[] args) {
        StdOut.println("Start.");
    }
}
