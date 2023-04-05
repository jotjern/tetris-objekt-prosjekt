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
        Stage stage = (Stage) nameField.getScene().getWindow();
        System.out.println(stage);
        System.out.println(nameField);
        Object userData = stage.getUserData();
        if (userData instanceof HashMap) {
            HashMap<String, Object> data = (HashMap<String, Object>) userData;
            if (data.containsKey("score")) {
                int score = (int) data.get("score");
                scoreLabel.setText("Score: " + score);
            } else {
                scoreLabel.setText("Score: 0");
            }
        } else {
            scoreLabel.setText("Score: 0");
        }
    }

    public void saveScore() {

    }

    public void noSaveScore() {

    }
}
