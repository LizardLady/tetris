package com.example.tetris;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;

public class ScoreHandler {
    private StringProperty scoreText = new SimpleStringProperty();
    private int score = 0;
    public ScoreHandler(StringProperty label) {
        scoreText.bindBidirectional(label);
    }

    public void handler(int size) {
        if (size > 0) {
            score += size * 1000;
            scoreText.setValue(String.valueOf(score));
        } else {
            score += 10;
            scoreText.setValue(String.valueOf(score));
        }
    }
}
