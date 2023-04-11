package com.example.tetris;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.List;

public class TetrisGame {
    int width = 10;
    int height = 10;

    private SolidifiedGrid solidifiedGrid;
    private TetrisBlock currentBlock;

    private int score = 0;

    private boolean gameOver = false;


    public TetrisGame() {
        this(10, 10);
    }

    public TetrisGame(int width, int height) {
        solidifiedGrid = new SolidifiedGrid(width, height);
        currentBlock = new TetrisBlock(width / 2, 0);
    }


    void draw(GraphicsContext ctx, int width, int height) {
        draw(ctx, width, height, false);
    }

    void draw(GraphicsContext ctx, int width, int height, boolean konami) {
        ctx.setFill(Color.WHITE);
        ctx.fillRect(0, 0, width, height);

        double cellWidth = (int)((double)width / this.width);

        for (ColorGrid grid : List.of(solidifiedGrid, currentBlock)) {
            Color[][] colors = grid.getGrid(this.width, this.height);
            for (int x = 0; x < this.width; x++) {
                for (int y = 0; y < this.height; y++) {
                    if (colors[x][y] != null) {
                        if (konami) {
                            ctx.setFill(Color.rgb(
                                    (int) (Math.random() * 255),
                                    (int) (Math.random() * 255),
                                    (int) (Math.random() * 255)
                            ));
                        } else {
                            ctx.setFill(colors[x][y]);
                        }
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
                this.gameOver = true;
            }
        }
    }

    public void moveLeft() {
        currentBlock.tryMove(-1, 0, solidifiedGrid.getGrid(width, height));
    }

    public void moveRight() {
        currentBlock.tryMove(1, 0, solidifiedGrid.getGrid(width, height));
    }

    public void moveDown() {
        currentBlock.tryMove(0, 1, solidifiedGrid.getGrid(width, height));
    }

    public void rotateLeft() {
        currentBlock.tryRotate(false, solidifiedGrid.getGrid(width, height));
    }

    public void rotateRight() {
        currentBlock.tryRotate(true, solidifiedGrid.getGrid(width, height));
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
