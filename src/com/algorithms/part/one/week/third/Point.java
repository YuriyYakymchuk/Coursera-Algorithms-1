package com.algorithms.part.one.week.third;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(x, y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */

    public double slopeTo(Point that) {
        if (x == that.x && y == that.y) {
            return Double.NEGATIVE_INFINITY;
        }
        if (x == that.x) {
            return Double.POSITIVE_INFINITY;
        }
        if (y == that.y) {
            return 0.0;
        }
        return (double) (that.y - y)/(that.x - x);
    }

    @Override
    public int compareTo(Point that) {
        if (y < that.y) {
            return -1;
        }
        if (y > that.y) {
            return 1;
        }
        if (x < that.x) {
            return -1;
        }
        if (x > that.x) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new SlopComparator();
    }

    private class SlopComparator implements Comparator<Point> {

        @Override
        public int compare(Point first, Point second) {
            if (slopeTo(first) < slopeTo(second)) {
                return -1;
            }
            if (slopeTo(first) > slopeTo(second)) {
                return 1;
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println("Start");
    }
}
