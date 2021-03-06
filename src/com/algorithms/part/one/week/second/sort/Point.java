package com.algorithms.part.one.week.second.sort;

public class Point implements Comparable<Point> {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public int compareTo(Point o) {
        if (o == null) {
            throw new IllegalArgumentException();
        }
        if (this.x > o.x) {
            return 1;
        }
        if (this.x < o.x) {
            return -1;
        }
        if (this.y > o.y) {
            return 1;
        }
        if (this.y < o.y) {
            return -1;

        }
        return 0;
    }
}
