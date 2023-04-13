package com.example.tetris;

import javafx.event.ActionEvent;

import java.io.IOException;

public class StartViewController {
    public void startGame(ActionEvent event) throws IOException {
        Application.setScene("play-view.fxml");
    }

    public void viewHighScores(ActionEvent event) throws IOException {
        Application.setScene("scoreboard-view.fxml");
    }

    public void exitGame() {
        System.exit(0);
    }
}
