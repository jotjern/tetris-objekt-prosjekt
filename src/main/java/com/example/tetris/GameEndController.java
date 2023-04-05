package com.example.tetris;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;

public class GameEndController {
    @FXML
    private TextField nameField;

    @FXML
    private Label scoreLabel;

    public void initialize() {
        scoreLabel.setText("Score: " + Application.getScore());
    }

    public void saveScore() {

    }

    public void noSaveScore() {

    }
}
