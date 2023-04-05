package com.example.tetris;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.io.IOException;

public class ScoreboardController {
    @FXML
    private GridPane scoreboardGrid;

    public void onContinue() throws IOException {
        Application.setScene("start-view.fxml");
    }

    public void initialize() {
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.addScore("John", 100);
        scoreboard.addScore("Jane", 200);
        scoreboard.addScore("Jack", 300);

        // Iterate over children of the grid pane

        for (Node node : scoreboardGrid.getChildren()) {
            if (node instanceof Label) {
                Label label = (Label) node;

                Object columnIndex = label.getProperties().get("gridpane-column");
                Object rowIndex = label.getProperties().get("gridpane-row");

                if (columnIndex instanceof Integer && rowIndex instanceof Integer) {
                    Pair<String, Integer> score = scoreboard.getScore((int)rowIndex - 1);

                    if (score == null)
                        continue;

                    if (columnIndex.equals(0)) {
                        label.setText(score.getKey());
                    } else if (columnIndex.equals(1)) {
                        label.setText(score.getValue().toString());
                    }
                }
            }
        }
    }
}
