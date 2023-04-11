package com.example.tetris;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class Application extends javafx.application.Application {
    private static HashMap<String, URL> sceneMap = new HashMap<>();
    private static Stage stage;
    private static int score = 0;

    private void loadScene(String filename) throws IOException {
        URL scene = Application.class.getResource(filename);

        if (scene == null)
            throw new IllegalArgumentException("No scene with filename " + filename + " exists.");

        sceneMap.put(filename, scene);
    }

    public static void setScene(String filename) throws IOException {
        stage.close();
        URL scene = sceneMap.get(filename);
        if (scene == null)
            throw new IllegalArgumentException("No scene with filename " + filename + " has been preloaded.");

        stage.setScene(new Scene(FXMLLoader.load(scene)));
        stage.show();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Application.stage = stage;

        try {
            loadScene("start-view.fxml");
            loadScene("play-view.fxml");
            loadScene("game-end-view.fxml");
            loadScene("scoreboard-view.fxml");
        } catch (IOException e) {
            System.out.println("Missing scene file(s)");
            System.out.println(e.getMessage());
            System.exit(1);
        }

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