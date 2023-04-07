package com.example.tetris;

import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
        try {
            // Open the file containing the scores
            File file = new File("scores.txt");
            Scanner scanner = new Scanner(file);

            // Read each line and add the score to the scores list
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                String name = parts[0];
                int score = Integer.parseInt(parts[1]);
                addScore(name, score);
            }

            // Close the scanner
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Scores file not found");
        }
    }

    public void saveScores() {
        try {
            // Open a file writer to write the scores to a file
            FileWriter writer = new FileWriter("scores.txt");

            // Write each score to a line in the file
            for (Pair<String, Integer> score : scores) {
                String line = score.getKey() + " " + score.getValue();
                writer.write(line + "\n");
            }

            // Close the writer
            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving scores");
        }
    }
}
