package com.example.tetris;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class Application extends javafx.application.Application {
    private static HashMap<String, FXMLLoader> sceneMap = new HashMap<>();
    private static Stage stage;
    private static int score = 0;

    private void loadScene(String filename) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(filename));

        sceneMap.put(filename, fxmlLoader);
    }

    public static void setScene(String filename) throws IOException {
        setScene(filename, null);
    }

    public static void setScene(String filename, Object data) throws IOException {
        FXMLLoader loader = sceneMap.get(filename);
        if (loader == null)
            throw new IllegalArgumentException("No scene with filename " + filename + " has been preloaded.");
        Scene scene = new Scene(loader.load(), 800, 800);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Application.stage = stage;

        loadScene("start-view.fxml");
        loadScene("play-view.fxml");
        loadScene("game-end-view.fxml");

        Application.stage.setTitle("Tetris");
        Application.setScene("start-view.fxml");
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Application.score = score;
    }

    public static void main(String[] args) {
        launch();
    }
}