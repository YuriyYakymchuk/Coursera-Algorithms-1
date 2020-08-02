package com.algorithms.part.one.week.first;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double COEFFICIENT = 1.96;

    private final int size;
    private final double mean;
    private final double deviation;
    private double[] trials;

    public PercolationStats(int n, int numberOfTrials) {
        if (n <= 0 || numberOfTrials <= 0) {
            throw new IllegalArgumentException("Parameters 'n' and 'trials' can't be negative.");
        }
        size = n;
        trials = new double[numberOfTrials];

        for (int i = 0; i < numberOfTrials; i++) {
            performTrial(i);
        }
        mean = StdStats.mean(trials);
        deviation = StdStats.stddev(trials);
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return deviation;
    }

    public double confidenceLo() {
        return mean - (COEFFICIENT * deviation / Math.sqrt(trials.length));
    }

    public double confidenceHi() {
        return mean + (COEFFICIENT * deviation / Math.sqrt(trials.length));
    }

    private void performTrial(int index) {
        Percolation percolation = new Percolation(size);
        while (!percolation.percolates()) {
            percolation.open(StdRandom.uniform(size) + 1, StdRandom.uniform(size) + 1);
        }
        trials[index] = ((double) percolation.numberOfOpenSites() / (size * size));
    }

    public static void main(String[] args) {
        if (args == null || args.length != 2) {
            throw new IllegalArgumentException("Input parametrs are missed.");
        }
        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println(String.format("mean                    = %s", percolationStats.mean()));
        System.out.println(String.format("stddev                  = %s", percolationStats.stddev()));
        System.out.println(String.format("95%% confidence interval = [%s, %s]", percolationStats.confidenceLo(),
                percolationStats.confidenceHi()));
    }

}
