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
        Painter.registerGraphicsContext(gc);
        GameEngine engine = new GameEngine();
        DaemonGameThread thread = new DaemonGameThread(engine);
        thread.start();






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
        });


    }
}