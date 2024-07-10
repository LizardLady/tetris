package com.example.tetris;

import com.example.tetris.figure.*;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Canvas mainCanvas;
    @FXML
    private VBox mainWindow;

    @FXML
    protected void onHelloButtonClick() throws InterruptedException {
        welcomeText.setText("Welcome to JavaFX Application!");
        GraphicsContext gc = mainCanvas.getGraphicsContext2D();
        Figure figure = new LRightFigure(3, 3, gc);
        figure.draw();
        mainCanvas.setFocusTraversable(true);
        mainWindow.setOnKeyTyped(keyEvent -> {
            System.out.println(keyEvent.getCharacter());
            if (keyEvent.getCharacter().equals("s")) {
                figure.clear();
                figure.moveDown();
                figure.draw();
            }
            if (keyEvent.getCharacter().equals("a")) {
                figure.clear();
                figure.moveLeft();
                figure.draw();
            }
            if (keyEvent.getCharacter().equals("d")) {
                figure.clear();
                figure.moveRight();
                figure.draw();
            }
            if (keyEvent.getCharacter().equals("r")) {
                figure.clear();
                figure.rotate();
                figure.draw();
            }
        });

    }
}