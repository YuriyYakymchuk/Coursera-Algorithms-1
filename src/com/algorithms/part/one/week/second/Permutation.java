package com.algorithms.part.one.week.second;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Permutation {

    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        int k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            queue.enqueue(s);
        }
        Iterator<String> queueIterator = queue.iterator();
        for (int i = 0; i < k; i++) {
            StdOut.println(queueIterator.next());
        }
    }
}
