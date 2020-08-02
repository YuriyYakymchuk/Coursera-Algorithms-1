package com.algorithms.part.one.week.fifth.trees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.Stack;

public class PointSET {

    private final SET<Point2D> points;

    public PointSET() {
        points = new SET<>();
    }

    public boolean isEmpty() {
        return size() <= 0;
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D point) {
        validatePoint(point);
        if (!contains(point)) {
            points.add(point);
        }
    }

    public boolean contains(Point2D point) {
        validatePoint(point);
        return points.contains(point);
    }

    public void draw() {
        for (Point2D point : points) {
            point.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rectangle) {
        if (rectangle == null) {
            throw new IllegalArgumentException("Rectangle can't be null.");
        }
        Stack<Point2D> rectanglePoints = new Stack<>();
        for (Point2D point : points) {
            if (rectangle.contains(point)) {
                rectanglePoints.push(point);
            }
        }
        return rectanglePoints;
    }

    public Point2D nearest(Point2D point) {
        validatePoint(point);
        if (isEmpty()) {
            return null;
        }
        Iterator<Point2D> iterator = points.iterator();
        Point2D minPoint = iterator.next();
        while (iterator.hasNext()) {
            Point2D nextPoint = iterator.next();
            if (point.distanceSquaredTo(nextPoint) < point.distanceSquaredTo(minPoint)) {
                minPoint = nextPoint;
            }
        }
        return minPoint;
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
