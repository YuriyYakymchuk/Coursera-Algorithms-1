package com.algorithms.week.fourth;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class Solver {

    private boolean solvable;
    private Node finalNode;
    private int numberOfMoves;

    private class Node {
        private final Board board;
        private final Node prevNode;

        public Node(Board board, Node prevNode) {
            this.board = board;
            this.prevNode = prevNode;
        }
    }

    private class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node a, Node b) {
            return a.board.manhattan() + numMoves(a) - b.board.manhattan() - numMoves(b);
        }
    }

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("Board can't be null.");
        }

        solvable = false;

        NodeComparator nodeComparator = new NodeComparator();
        MinPQ<Node> pq = new MinPQ<>(nodeComparator);
        MinPQ<Node> twinPQ = new MinPQ<>(nodeComparator);

        Node node = new Node(initial, null);
        Node twinNode = new Node(initial.twin(), null);

        pq.insert(node);
        twinPQ.insert(twinNode);

        node = pq.delMin();
        twinNode = twinPQ.delMin();

        while (!node.board.isGoal() && !twinNode.board.isGoal()) {
            for (Board neighbor : node.board.neighbors()) {
                if (node.prevNode == null || !node.prevNode.board.equals(neighbor)) {
                    pq.insert(new Node(neighbor, node));
                }
            }

            for (Board neighbor : twinNode.board.neighbors()) {
                if (twinNode.prevNode == null || !twinNode.prevNode.board.equals(neighbor)) {
                    twinPQ.insert(new Node(neighbor, twinNode));
                }
            }

            node = pq.delMin();
            twinNode = twinPQ.delMin();
            numberOfMoves++;
        }

        if (node.board.isGoal()) {
            solvable = true;
            finalNode = node;
            numberOfMoves++;
        }
    }

    public boolean isSolvable() {
        return solvable;
    }

    public int moves() {
        if (!solvable) {
            return -1;
        }
        int moves = 0;
        Node currentNode = finalNode;
        while (currentNode.prevNode != null) {
            currentNode = currentNode.prevNode;
            moves++;
        }
        return moves;
    }

    public Iterable<Board> solution() {
        Board[] solution = new Board[numberOfMoves];
        Node currentNode = finalNode;
        int index = numberOfMoves - 1;
        do {
            solution[index--] = currentNode.board;
            currentNode = currentNode.prevNode;
        } while (currentNode != null && index >= 0);
        return Arrays.asList(solution);
    }

    private static int numMoves(Node node) {
        int numMoves = 0;
        Node currentNode = node;
        while (currentNode.prevNode != null) {
            currentNode = currentNode.prevNode;
            numMoves++;
        }
        return numMoves;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int dimension = in.readInt();

        int[][] blocks = new int[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                blocks[i][j] = in.readInt();
            }
        }

        Board initial = new Board(blocks);
        Solver solver = new Solver(initial);

        if (!solver.isSolvable()) {
            StdOut.println("No solution possible.");
        }
        else {
            StdOut.println("Minimum number of numberOfMoves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        }
    }
}
