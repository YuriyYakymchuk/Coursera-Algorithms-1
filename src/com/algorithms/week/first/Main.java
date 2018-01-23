package com.algorithms.week.first;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final Pattern pattern = Pattern.compile("\\s(?<first>\\d+)\\s+(?<second>\\d+)\\s*");

    public static void main(String[] args) throws IOException{
        Iterator<String> lines = Files.readAllLines(Paths.get("/Users/yyakymchuk/Downloads/percolation/input10.txt")).iterator();
        Percolation percolation = new Percolation(Integer.parseInt(lines.next()));
        while (lines.hasNext()) {
            Matcher m = pattern.matcher(lines.next());
            if (m.find()) {
                percolation.open(Integer.parseInt(m.group("first")), Integer.parseInt(m.group("second")));
            }
        }
        int p = 9, q = 1;
        System.out.println(String.format("Dots '%d' and '%d' are connected: %s", p, q, percolation.isFull(p, q)));
    }
}
