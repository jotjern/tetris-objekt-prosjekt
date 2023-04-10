package com.example.tetris;

import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class ScoreboardTest {
    private Scoreboard scoreboard;
    private File file;
    private static final int MAX_SCORES = 10; // Needs to be the same as in Scoreboard for tests to work

    @BeforeEach
    void setUp() throws IOException {
        scoreboard = new Scoreboard();
        file = new File("scoresTest.txt");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
    }

    @AfterEach
    void tearDown() {
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testAddScore() {
        // Just tests that scores are saved correctly
        scoreboard.addScore("Alice", 100);
        assertEquals(100, scoreboard.getScore(0).getValue());

        scoreboard.addScore("Bob", 50);
        scoreboard.addScore("Charlie", 75);
        scoreboard.addScore("Dave", 125);
        scoreboard.addScore("Eve", 90);
        assertEquals(50, scoreboard.getScore(4).getValue());

        scoreboard.addScore("Frank", 200);
        assertEquals(200, scoreboard.getScore(0).getValue());
    }

    @Test
    void testLoadScores() throws FileNotFoundException {
        // Tests that scores are read from file and stored in array
        PrintWriter writer = new PrintWriter(file);
        writer.println("Alice 100");
        writer.println("Bob 50");
        writer.println("Charlie 75");
        writer.close();

        scoreboard.loadScores("scoresTest.txt");
        assertEquals(50, scoreboard.getScore(2).getValue());
        assertEquals(100, scoreboard.getScore(0).getValue());
    }

    @Test
    void testSaveScores() throws IOException {
        // Tests that scores are saved in correct format and in correct order
        scoreboard.addScore("Alice", 100);
        scoreboard.addScore("Bob", 50);
        scoreboard.addScore("Charlie", 75);

        scoreboard.saveScores("scoresTest.txt");

        Scanner scanner = new Scanner(file);
        assertEquals("Alice 100", scanner.nextLine());
        assertEquals("Charlie 75", scanner.nextLine());
        assertEquals("Bob 50", scanner.nextLine());
        scanner.close();
    }

    @Test
    void testMaxScores() {
        // Tests that number of scores will not exceed MAX_SCORES
        for (int i = 0; i < MAX_SCORES + 5; i++) {
            scoreboard.addScore("Player " + i, i * 10);
        }
        assertEquals(null, scoreboard.getScore(MAX_SCORES));
        assertEquals(MAX_SCORES, scoreboard.getSize());
    }

    @Test
    void testSortOrder() {
        // Tests that scores are saved in correct order
        scoreboard.addScore("Alice", 100);
        scoreboard.addScore("Bob", 50);
        scoreboard.addScore("Charlie", 75);
        scoreboard.addScore("Dave", 125);
        scoreboard.addScore("Eve", 90);

        assertEquals(125, scoreboard.getScore(0).getValue());
        assertEquals(50, scoreboard.getScore(4).getValue());
    }

    @Test
    void testRepeatedNames() {
        // Tests that the same same can be saved repeatedly
        scoreboard.addScore("Alice", 100);
        scoreboard.addScore("Bob", 50);
        scoreboard.addScore("Alice", 125);

        assertEquals("Alice", scoreboard.getScore(0).getKey());
        assertEquals("Alice", scoreboard.getScore(1).getKey());
    }
}
