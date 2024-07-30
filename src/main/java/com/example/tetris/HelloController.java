package com.example.tetris;

import com.example.tetris.figure.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class HelloController {
    @FXML
    private Canvas nextFigureCanvas;
    @FXML
    private Label welcomeText;
    @FXML
    private Canvas mainCanvas;
    @FXML
    private VBox mainWindow;


    @FXML
    protected void onHelloButtonClick() throws InterruptedException {
        GraphicsContext gc = mainCanvas.getGraphicsContext2D();
        Painter.registerGraphicsContext(gc);
        GameEngine engine = new GameEngine(welcomeText.textProperty());


        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000 / Settings.SPEED), event -> {
            if (engine.isGame() && engine.tick()) {
                System.out.println("tick");
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        mainCanvas.setFocusTraversable(true);
        mainWindow.setOnKeyTyped(keyEvent -> {
            System.out.println(keyEvent.getCharacter());
            if (!engine.isGame()) {
                return;
            }
            if (keyEvent.getCharacter().equals("s")) {
                engine.tick();
            }
            if (keyEvent.getCharacter().equals("a")) {
                engine.moveLeft();
            }
            if (keyEvent.getCharacter().equals("d")) {
                engine.moveRight();
            }
            if (keyEvent.getCharacter().equals("r")) {
                engine.rotate();
            }
            if (keyEvent.getCharacter().equals("w")) {
                engine.dropDown();
            }
        });

    }
}