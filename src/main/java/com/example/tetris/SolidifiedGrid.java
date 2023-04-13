package com.example.tetris;

import javafx.scene.paint.Color;

public class SolidifiedGrid implements ColorGrid {
    private Color[][] solidified;

    int width;
    int height;

    SolidifiedGrid(int width, int height) {
        this.solidified = new Color[width][height];
        this.width = width;
        this.height = height;
    }

    public Color get(int x, int y) {
        return solidified[x][y];
    }

    public void set(int x, int y, Color color) {
        solidified[x][y] = color;
    }

    public int merge(ColorGrid grid) {
        Color[][] colors = grid.getGrid(solidified.length, solidified[0].length);

        assert colors.length == solidified.length;
        assert colors[0].length == solidified[0].length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (get(x, y) == null && colors[x][y] != null) {
                    set(x, y, colors[x][y]);
                }
            }
        }

        int n_cleared = 0;

        for (int y = 0; y < height; y++) {
            boolean full = true;
            for (int x = 0; x < width; x++) {
                if (solidified[x][y] == null) {
                    full = false;
                    break;
                }
            }

            if (full) {
                n_cleared++;
                for (int x = 0; x < width; x++) {
                    solidified[x][y] = null;
                }

                for (int yy = y; yy > 0; yy--) {
                    for (int x = 0; x < width; x++) {
                        solidified[x][yy] = solidified[x][yy - 1];
                    }
                }
            }
        }

        return (int) Math.pow(n_cleared, 2);
    }

    @Override
    public Color[][] getGrid(int w, int h) {
        Color[][] grid = new Color[w][h];
        for (int x = 0; x < w; x++) {
            System.arraycopy(solidified[x], 0, grid[x], 0, h);
        }
        return grid;
    }
}
