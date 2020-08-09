package com.algorithms.part.two.week.second;

import edu.princeton.cs.algs4.Picture;

import java.awt.Color;

public class SeamCarver {
    private static final boolean HORIZONTAL = true;
    private static final boolean VERTICAL   = false;

    private Picture picture;
    private double[] distTo;
    private int[][] edgeTo;

    public SeamCarver(Picture picture) {
        if (picture == null) {
            throw new IllegalArgumentException();
        }

        this.picture = new Picture(picture);
    }

    public Picture picture() {
        return new Picture(picture);
    }

    public int width() {
        return picture.width();
    }

    public int height() {
        return picture.height();
    }

    public double energy(int x, int y) {
        if (x < 0 || y < 0 || x > width() - 1 || y > height() - 1) {
            throw new IllegalArgumentException();
        }

        if (x == 0 || y == 0 || x == width() - 1 || y == height() - 1) {
            return 1000;
        }

        Color top = picture.get(x, y - 1);
        Color bottom = picture.get(x, y + 1);
        Color left = picture.get(x - 1, y);
        Color right = picture.get(x + 1, y);

        return Math.sqrt(squareGradient(top, bottom) + squareGradient(left, right));
    }

    private double squareGradient(Color first, Color second) {
        return Math.pow(first.getRed() - second.getRed(), 2) +
                Math.pow(first.getGreen() - second.getGreen(), 2) +
                Math.pow(first.getBlue()  - second.getBlue(), 2);
    }

    public int[] findHorizontalSeam() {
        return seam(HORIZONTAL);
    }

    public int[] findVerticalSeam() {
        return seam(VERTICAL);
    }

    private int[] seam(boolean direction) {
        distTo = (direction == VERTICAL) ? new double[width()] : new double[height()];
        edgeTo = new int[width()][height()];

        for (int i = 0; i < this.distTo.length; i++) {
            distTo[i] = 1000;
        }

        int maxI = (direction == VERTICAL) ? height() : width();
        int maxJ = (direction == VERTICAL) ? width() : height();

        for (int i = 1; i < maxI; i++) {
            double[] lastDistTo = distTo.clone();
            for (int k = 0; k < distTo.length; k++) {
                distTo[k] = Double.POSITIVE_INFINITY;
            }

            for (int j = 1; j < maxJ; j++) {
                int x = (direction == VERTICAL) ? j : i;
                int y = (direction == VERTICAL) ? i : j;

                double energy = energy(x, y);

                relax(j - 1, x, y, energy, lastDistTo, direction);
                relax(j, x, y, energy, lastDistTo, direction);
                relax(j + 1, x, y, energy, lastDistTo, direction);
            }
        }

        double minWeight = Double.POSITIVE_INFINITY;
        int min = 0;

        for (int i = 0; i < distTo.length; i++) {
            double weight = distTo[i];
            if (weight < minWeight) {
                min = i;
                minWeight = weight;
            }
        }

        int[] seam = (direction == VERTICAL) ? new int[height()] : new int[width()];

        if (direction == VERTICAL) {
            for (int y = height() - 1; y >= 0; y--) {
                seam[y] = min;
                min = edgeTo[min][y];
            }
        } else {
            for (int x = width() - 1; x >= 0; x--) {
                seam[x] = min;
                min = edgeTo[x][min];
            }
        }

        return seam;
    }
    private void relax(int prev, int x, int y, double energy, double[] lastDistTo, boolean direction) {
        if (prev < 0 || prev >= lastDistTo.length) {
            return;
        }

        double weight = lastDistTo[prev];

        int index = (direction == VERTICAL) ? x : y;
        if (distTo[index] > weight + energy) {
            distTo[index] = weight + energy;
            edgeTo[x][y] = prev;
        }
    }

    public void removeHorizontalSeam(int[] seam) {
        if (seam == null || height() <= 1 || seam.length != this.width()) {
            throw new IllegalArgumentException();
        }

        Picture newPicture = new Picture(width(), height() - 1);

        int prevSeam = seam[0];

        for (int x = 0; x < width(); x++) {
            if (Math.abs(seam[x] - prevSeam) > 1 || seam[x] < 0 || seam[x] >= height()) {
                throw new IllegalArgumentException();
            }
            prevSeam = seam[x];

            for (int y = 0; y < height(); y++) {
                if (seam[x] == y) {
                    continue;
                }

                Color color = picture.get(x, y);
                newPicture.set(x, seam[x] > y ? y : y - 1, color);
            }
        }

        picture = newPicture;
    }

    public void removeVerticalSeam(int[] seam) {
        if (seam == null || width() <= 1 || seam.length != height()) {
            throw new IllegalArgumentException();
        }

        Picture newPicture = new Picture(width() - 1, height());

        int prevSeam = seam[0];

        for (int y = 0; y < height(); y++) {
            if (Math.abs(seam[y] - prevSeam) > 1 || seam[y] < 0 || seam[y] >= width()) {
                throw new IllegalArgumentException();
            }
            prevSeam = seam[y];

            for (int x = 0; x < width(); x++) {
                if (seam[y] == x) {
                    continue;
                }

                Color color = picture.get(x, y);
                newPicture.set(seam[y] > x ? x : x - 1, y, color);
            }
        }

        picture = newPicture;
    }
}