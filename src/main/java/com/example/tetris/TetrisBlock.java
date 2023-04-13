package com.example.tetris;

import javafx.scene.paint.Color;

public class TetrisBlock implements ColorGrid {
    private Color color;
    private int x;
    private int y;

    private int[][] shape;

    private static final int[][][] SHAPES = {
            { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } }, // square
            { { 0, -1 }, { 0, 0 }, { 0, 1 }, { 0, 2 } }, // line
            { { -1, 0 }, { -1, 1 }, { 0, 0 }, { 1, 0 } }, // L
            { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 } }, // reverse L
            { { -1, 0 }, { 0, 0 }, { 0, 1 }, { 1, 1 } }, // Z
            { { -1, 1 }, { 0, 1 }, { 0, 0 }, { 1, 0 } }, // reverse Z
            { { 0, 0 }, { 1, -1 }, { 1, 0 }, { 1, 1 } }, // T
    };

    public TetrisBlock(int x, int y) {
        this(x, y, Color.hsb(Math.random() * 360, 1, 1));
    }

    public TetrisBlock(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.shape = SHAPES[(int) (Math.random() * SHAPES.length)];
    }

    public boolean tryMove(int dx, int dy, Color[][] solidified) {
        x += dx;
        y += dy;

        if (isCollided(solidified)) {
            x -= dx;
            y -= dy;
            return false;
        }

        return true;
    }

    public boolean tryRotate(boolean clockwise, Color[][] solidified) {
        rotate(clockwise);
        if (isCollided(solidified)) {
            rotate(!clockwise);
            return false;
        }
        return true;
    }

    private void rotate(boolean clockwise) {
        for (int[] point : shape) {
            int x = point[0];
            int y = point[1];

            if (clockwise) {
                point[0] = y;
                point[1] = -x;
            } else {
                point[0] = -y;
                point[1] = x;
            }
        }
    }

    public boolean isCollided(Color[][] solidified) {
        for (int[] point : shape) {
            int x = point[0] + this.x;
            int y = point[1] + this.y;

            // If the point is out of bounds, it counts as colliding with the wall except
            // for the top
            if (x < 0 || x >= solidified.length || y >= solidified[0].length)
                return true;

            if (y >= 0 && solidified[x][y] != null)
                return true;
        }
        return false;
    }

    @Override
    public Color[][] getGrid(int w, int h) {
        Color[][] grid = new Color[w][h];

        for (int[] point : shape) {
            int x = point[0] + this.x;
            int y = point[1] + this.y;

            if (x >= 0 && x < w && y >= 0 && y < h)
                grid[x][y] = color;
        }

        return grid;
    }
}
