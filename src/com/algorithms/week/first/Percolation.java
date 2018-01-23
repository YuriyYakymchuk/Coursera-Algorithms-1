package com.algorithms.week.first;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final boolean OPEN = true;
    private static final boolean CLOSED = false;

    private final int size;
    private final int width;

    private boolean[] sites;
    private int numberOfOpenSites;

    private final WeightedQuickUnionUF uf;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Number of sites can't be negative.");
        }
        size = n * n;
        width = n;
        sites = new boolean[size];
        uf = new WeightedQuickUnionUF(size + 2);
    }

    public void open(int row, int col) {
        checkBounds(row, col);
        int index = convert2Dto1D(row, col);
        if (sites[index] == CLOSED) {
            sites[index] = OPEN;
            numberOfOpenSites++;
            connectNeighbors(row, col);
        }
        System.out.println(String.format("Opening row: %d, col: %d. 9:1 isFull: %s", row, col, isFull(9, 1)));
    }

    public boolean percolates() {
        return uf.connected(size, size + 1);
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean isFull(int row, int col) {
        checkBounds(row, col);
        return uf.connected(convert2Dto1D(row, col), size);
    }

    public boolean isOpen(int row, int col) {
        checkBounds(row, col);
        return sites[convert2Dto1D(row, col)] == OPEN;
    }

    private void connectNeighbors(int row, int col) {
        int index = convert2Dto1D(row, col);

        if (col < width) {
            attemptUnion(row, col + 1, index);
        }
        if (col > 1) {
            attemptUnion(row, col - 1, index);
        }
        if (row < width) {
            attemptUnion(row + 1, col, index);
        }
        else {
            uf.union(index, size + 1);
        }
        if (row > 1) {
            attemptUnion(row - 1, col, index);
        }
        else {
            uf.union(index, size);
        }
    }

    private void attemptUnion(int row, int col, int index) {
        int neighborIndex = convert2Dto1D(row, col);
        if (sites[neighborIndex] == OPEN) {
            uf.union(neighborIndex, index);
        }
    }

    private int convert2Dto1D(int row, int col) {
        return width * (row - 1) + col - 1;
    }

    private void checkBounds(int row, int col) {
        if (row <= 0 || row > width || col <= 0 || col > width) {
            throw new IllegalArgumentException("Row or Column is out of bounds.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Start");
    }
}
