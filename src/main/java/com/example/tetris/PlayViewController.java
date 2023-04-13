package com.example.tetris;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;
import static javafx.scene.input.KeyEvent.KEY_RELEASED;

public class PlayViewController {
    @FXML
    public Canvas canvas;

    private Timeline timeline;
    private GraphicsContext ctx;

    private int konamiCodeIndex = 0;
    private boolean konami = false;
    private TetrisGame game;

    private HashSet<KeyCode> pressedKeys = new HashSet<>();
    private static final ArrayList<KeyCode> konamiCode = new ArrayList<>(List.of(
            KeyCode.UP, KeyCode.UP, KeyCode.DOWN, KeyCode.DOWN, KeyCode.LEFT,
            KeyCode.RIGHT, KeyCode.LEFT, KeyCode.RIGHT, KeyCode.B, KeyCode.A
    ));

    public void initialize() {
        ctx = canvas.getGraphicsContext2D();

        game = new TetrisGame();

        canvas.setFocusTraversable(true);
        canvas.addEventHandler(KEY_PRESSED, e -> {
            if (pressedKeys.contains(e.getCode()))
                return;

            pressedKeys.add(e.getCode());

            if (!konami) {
                if (e.getCode() == konamiCode.get(konamiCodeIndex)) {
                    konamiCodeIndex++;
                    System.out.println(konamiCodeIndex);
                    if (konamiCodeIndex == konamiCode.size())
                        konami = true;
                    return;
                } else {
                    konamiCodeIndex = 0;
                }
            }

            switch (e.getCode()) {
                case LEFT -> game.moveLeft();
                case RIGHT -> game.moveRight();
                case DOWN -> game.moveDown();
                case R -> game.rotateRight();
                case T -> game.rotateLeft();
                default -> { return; }
            }

            game.draw(ctx, (int) canvas.getWidth(), (int) canvas.getHeight(), konami);
        });

        canvas.addEventHandler(KEY_RELEASED, e -> pressedKeys.remove(e.getCode()));

        timeline = new Timeline(new KeyFrame(Duration.millis(500.0), e -> {
            try {
                game.step();
                game.draw(ctx, (int) canvas.getWidth(), (int) canvas.getHeight(), konami);

                if (game.isGameOver()) {
                    timeline.stop();
                    Application.setScore(game.getScore());
                    Application.setScene("game-end-view.fxml");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}