package com.example.tetris;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;
import static javafx.scene.input.KeyEvent.KEY_RELEASED;

public class PlayViewController {
    @FXML
    public Canvas canvas;

    private Timeline timeline;
    private GraphicsContext ctx;

    private int score = 0;

    int width = 10;
    int height = 10;

    private SolidifiedGrid solidifiedGrid;
    private TetrisBlock currentBlock;
    private HashSet<KeyCode> pressedKeys = new HashSet<>();

    void draw() {
        ctx.setFill(Color.WHITE);
        ctx.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        double cellWidth = canvas.getWidth() / width;

        for (ColorGrid grid : List.of(solidifiedGrid, currentBlock)) {
            Color[][] colors = grid.getGrid(width, height);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (colors[x][y] != null) {
                        ctx.setFill(colors[x][y]);
                        ctx.fillRect(x * cellWidth, y * cellWidth, cellWidth, cellWidth);
                        ctx.setFill(Color.BLACK);
                        ctx.strokeRect(x * cellWidth, y * cellWidth, cellWidth, cellWidth);
                    }
                }
            }
        }
    }

    public void step() throws IOException {
        if (!currentBlock.tryMove(0, 1, solidifiedGrid.getGrid(width, height))) {
            score += solidifiedGrid.merge(currentBlock);
            currentBlock = new TetrisBlock(width / 2, 0);
            if (currentBlock.isCollided(solidifiedGrid.getGrid(width, height))) {
                timeline.stop();

                Application.setScore(score);
                Application.setScene("game-end-view.fxml");
            }
        }

        draw();
    }

    public void initialize() throws IOException {
        ctx = canvas.getGraphicsContext2D();

        canvas.setFocusTraversable(true);
        canvas.addEventHandler(KEY_PRESSED, e -> {
            if (pressedKeys.contains(e.getCode()))
                return;
            pressedKeys.add(e.getCode());
            switch (e.getCode()) {
                case LEFT:
                    currentBlock.tryMove(-1, 0, solidifiedGrid.getGrid(width, height));
                    break;
                case RIGHT:
                    currentBlock.tryMove(1, 0, solidifiedGrid.getGrid(width, height));
                    break;
                case DOWN:
                    currentBlock.tryMove(0, 1, solidifiedGrid.getGrid(width, height));
                    break;
                case R:
                    currentBlock.tryRotate(true, solidifiedGrid.getGrid(width, height));
                    break;
                default:
                    return;
            }
            draw();
        });

        canvas.addEventHandler(KEY_RELEASED, e -> pressedKeys.remove(e.getCode()));

        solidifiedGrid = new SolidifiedGrid(width, height);
        currentBlock = new TetrisBlock(width / 2, 0);

        draw();

        int stepsPerSecond = 3;

        // render every 1/3 second
        timeline = new Timeline(new KeyFrame(Duration.millis(1000. / stepsPerSecond), e -> {
            try {
                step();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}