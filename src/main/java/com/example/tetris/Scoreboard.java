package com.example.tetris;

import javafx.util.Pair;

import java.util.ArrayList;

public class Scoreboard {
    private ArrayList<Pair<String, Integer>> scores = new ArrayList<>();

    public void addScore(String name, int score) {
        // TODO: Add score in the correct place, and remove the lowest score if there are more than 10
        scores.add(new Pair<>(name, score));
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
