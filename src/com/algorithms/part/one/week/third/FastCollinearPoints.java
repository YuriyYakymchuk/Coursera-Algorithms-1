package com.algorithms.part.one.week.third;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private final LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Points can't be null.");
        }

        int size = points.length;
        for (int i = 0; i < size; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("Point can't be null.");
            }
        }

        List<LineSegment> lineSegmentList = new ArrayList<>();
        Point[] copy = Arrays.copyOf(points, size);
        for (int i = 0; i < size; i++) {
            Point p = points[i];
            Arrays.sort(copy);
            Arrays.sort(copy, p.slopeOrder());

            int min = 0;
            while (min < size && p.slopeTo(copy[min]) == Double.NEGATIVE_INFINITY) {
                min++;
            }
            if (min != 1) {
                throw new IllegalArgumentException("Duplicate point.");
            }
            int max = min;
            while (min < size) {
                while (max < size && p.slopeTo(copy[max]) == p.slopeTo(copy[min])) {
                    max++;
                }
                if (max - min >= 3) {
                    Point pMin = copy[min].compareTo(p) < 0 ? copy[min] : p;
                    Point pMax = copy[max - 1].compareTo(p) > 0 ? copy[max - 1] : p;
                    if (p == pMin) {
                        lineSegmentList.add(new LineSegment(pMin, pMax));
                    }
                }
                min = max;
            }
        }
        lineSegments = lineSegmentList.toArray(new LineSegment[lineSegmentList.size()]);
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments.clone();
    }
}
