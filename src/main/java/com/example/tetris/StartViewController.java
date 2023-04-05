package com.example.tetris;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;

public class StartViewController {
    Configuration configuration;
    public void startGame(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("play-view.fxml"));
        stage.setScene(new Scene(fxmlLoader.load(), 800, 800));
    }

    public void viewHighScores(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("high-scores-view.fxml"));
        stage.setScene(new Scene(fxmlLoader.load(), 800, 800));
    }

    public void exitGame() {
        System.exit(0);
    }

    public void initialize() {
        configuration = Configuration.fromFileOrDefault(Path.of("config.txt"));
    }
}
