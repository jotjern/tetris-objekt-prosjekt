package com.example.tetris;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class GameEndController {
    @FXML
    private TextField nameField;

    @FXML
    private Label scoreLabel;

    public void initialize() {
        scoreLabel.setText("Score: " + Application.getScore());
    }

    public void saveScore() throws IOException {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.loadScores();
        scoreboard.addScore(nameField.getText(), Application.getScore());
        scoreboard.saveScores();
        Application.setScene("scoreboard-view.fxml");
    }

    public void noSaveScore() throws IOException {
        Application.setScene("start-view.fxml");
    }
}
