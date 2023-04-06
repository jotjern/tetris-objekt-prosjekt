package com.example.tetris;

import javafx.util.Pair;
import java.util.*;

public class Scoreboard {
    private ArrayList<Pair<String, Integer>> scores = new ArrayList<>();
    private static final int MAX_SCORES = 10;

    public void addScore(String name, int score) {

        // Adds the new score to the scores list
        Pair<String, Integer> newScore = new Pair<String, Integer>(name, score);
        scores.add(newScore);

        // Sorts the list in descending order
        Collections.sort(scores, new Comparator<Pair<String, Integer>>() {
            @Override
            public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        // If there are more than 10(MAX_SCORE) elements we remove the excess ones.
        if (scores.size() > MAX_SCORES) {
            scores.subList(MAX_SCORES, scores.size()).clear();
        }
    }

    public Pair<String, Integer> getScore(int index) {
        if (index < 0 || index >= scores.size())
            return null;
        return scores.get(index);
    }

    public void loadScores() {
        // TODO: Load scores from file
    }

    public void saveScores() {
        // TODO: Save scores to file
    }
}
