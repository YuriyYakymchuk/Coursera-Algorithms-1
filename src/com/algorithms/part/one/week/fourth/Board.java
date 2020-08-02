package com.algorithms.part.one.week.fourth;

import edu.princeton.cs.algs4.Stack;

public class Board {

    private final int dimension;
    private final int[][] data;

    public Board(int[][] data) {
        dimension = data.length;
        this.data = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.data[i][j] = data[i][j];
            }
        }
    }

    public int dimension() {
        return dimension;
    }

    public int hamming() {
        int numberOfWrongBlocks = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (i == dimension - 1 && j == dimension - 1) {
                    break;
                }
                if (data[i][j] != i * dimension + j + 1) {
                    numberOfWrongBlocks++;
                }
            }
        }
        return numberOfWrongBlocks;
    }

    public int manhattan() {
        int manhattanSum = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (data[i][j] == 0) {
                    continue;
                }
                int value = data[i][j];
                int row = (value - 1) / dimension;
                int col = (value - 1) % dimension;
                manhattanSum += Math.abs(i - row) + Math.abs(j - col);
            }
        }
        return manhattanSum;
    }

    public boolean isGoal() {
        if (data[dimension - 1][dimension - 1] != 0) {
            return false;
        }
        int num = 1;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (i == dimension - 1 && j == dimension - 1) {
                    break;
                }
                if (data[i][j] != num) {
                    return false;
                }
                num++;
            }
        }
        return true;
    }

    public Board twin() {
        Board board = new Board(data);
        if (board.data[0][0] == 0 || board.data[0][1] == 0) {
            swap(board.data, 1, 0, 1, 1);
        }
        else {
            swap(board.data, 0, 0, 0, 1);
        }
        return board;
    }

    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if (y == this) {
            return true;
        }
        if (y.getClass() != this.getClass()) {
            return false;
        }

        Board that = (Board) y;
        if (that.dimension != dimension) {
            return false;
        }

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (that.data[i][j] != data[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {
        Stack<Board> neighbors = new Stack<>();
        int row = 0, col = 0;
        int[][] temp = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (data[i][j] == 0) {
                    row = i;
                    col = j;
                }
                temp[i][j] = data[i][j];
            }
        }

        if (row > 0) {
            swap(temp, row, col, row - 1, col);
            neighbors.push(new Board(temp));
            swap(temp, row, col, row - 1, col);
        }
        if (row < dimension - 1) {
            swap(temp, row, col, row + 1, col);
            neighbors.push(new Board(temp));
            swap(temp, row, col, row + 1, col);
        }
        if (col > 0) {
            swap(temp, row, col - 1, row, col);
            neighbors.push(new Board(temp));
            swap(temp, row, col - 1, row, col);
        }
        if (col < dimension - 1) {
            swap(temp, row, col + 1, row, col);
            neighbors.push(new Board(temp));
            swap(temp, row, col + 1, row, col);
        }
        return neighbors;
    }

    public String toString() {
        StringBuilder output = new StringBuilder(dimension + "\n");
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                output.append(" " + data[i][j]);
            }
            output.append("\n");
        }
        return output.toString();
    }

    private static void swap(int[][] matrix, int i, int j, int p, int m) {
        int swap = matrix[i][j];
        matrix[i][j] = matrix[p][m];
        matrix[p][m] = swap;
    }

    public static void main(String[] args) {
        System.out.println("Main");
    }
}
