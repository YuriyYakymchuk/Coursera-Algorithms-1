package com.algorithms.part.one.week.third;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private final LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null || points[0] == null) {
            throw new IllegalArgumentException("Points can't be null");
        }
        int size = points.length;
        for (int i = 1; i < size; i++) {
            if (points[i] == null || points[i - 1].compareTo(points[i]) == 0) {
                throw new IllegalArgumentException("Point can't be null. There are shouldn't be duplicate points.");
            }
        }
        List<LineSegment> lineSegmentList = new ArrayList<>();
        for (int i = 0; i < size - 3; i++) {
            for (int j = i + 1; j < size - 2; j++) {
                for (int k = j + 1; k < size - 1; k++) {
                    for (int m = k + 1; m < size; m++) {
                        if (i < j && j < k && k < m) {
                            Point[] p = new Point[4];
                            p[0] = points[i];
                            p[1] = points[j];
                            p[2] = points[k];
                            p[3] = points[m];
                            if (p[0].slopeTo(p[1]) == p[0].slopeTo(p[2]) && p[0].slopeTo(p[1]) == p[0].slopeTo(p[3])) {
                                Arrays.sort(p);
                                lineSegmentList.add(new LineSegment(p[0], p[3]));
                            }
                        }
                    }
                }
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
