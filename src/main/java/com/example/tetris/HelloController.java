package com.example.tetris;

import com.example.tetris.figure.*;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Canvas mainCanvas;

    @FXML
    protected void onHelloButtonClick() throws InterruptedException {
        welcomeText.setText("Welcome to JavaFX Application!");
        GraphicsContext gc = mainCanvas.getGraphicsContext2D();
        Figure figure = new IFigure(3, 3, gc);
        Thread thread = new Thread(() -> {
            while (true) {
                figure.draw();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                figure.clear();
                figure.rotate();
            }
        }
        );

        thread.start();
    }
}