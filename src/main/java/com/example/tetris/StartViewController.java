package com.example.tetris;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;

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
