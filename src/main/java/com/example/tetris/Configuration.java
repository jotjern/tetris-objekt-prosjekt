package com.example.tetris;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Configuration {
    private int difficulty;

    public Configuration(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public static Configuration fromFile(Path filename) throws IOException {
        Configuration configuration = new Configuration(0);

        for (String line : Files.readAllLines(filename)) {
            String[] parts = line.split("=");
            if (parts[0].strip().equals("difficulty")) {
                configuration.setDifficulty(Integer.parseInt(parts[1].strip()));
            } else {
                System.out.println("Unknown configuration option: " + line.strip());
            }
        }

        return configuration;
    }

    public static Configuration fromFileOrDefault(Path filename) {
        try {
            return fromFile(filename);
        } catch (IOException e) {
            return new Configuration(0);
        }
    }

    public static void toFile(Configuration configuration, Path filename) throws IOException {
        String content = "difficulty = " + configuration.getDifficulty();
        Files.write(filename, content.getBytes());
    }
}
