package com.algorithms.part.two.week.first;

public class Outcast {

    private final WordNet wordNet;

    public Outcast(WordNet wordNet) {
        if (wordNet == null) {
            throw new IllegalArgumentException("WordNet can't be null.");
        }
        this.wordNet = wordNet;
    }

    public String outcast(String[] nouns) {
        if (nouns == null || nouns.length == 0) {
            throw new IllegalArgumentException("Invalid nouns.");
        }
        String result = null;
        int maxDistance = 0;
        for (String noun : nouns) {
            int distance = 0;
            for (String otherNoun : nouns) {
                distance += wordNet.distance(noun, otherNoun);
            }
            if (distance > maxDistance) {
                result = noun;
                maxDistance = distance;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Hello");
    }
}
