package com.example.tetris.figure;

import com.example.tetris.Painter;
import com.example.tetris.Settings;
import javafx.scene.canvas.GraphicsContext;

public class Cell {
    private int x;
    private int y;
    private Painter painter;

    public Cell(int x, int y, GraphicsContext context) {
        this.x = x;
        this.y = y;
        this.painter = new Painter(Settings.MAX_CELLS_WIDTH, Settings.MAX_CELLS_HEIGHT, context);
    }

    public void draw() {
        painter.drawSquare(x, y);
    }

    public void clear(){
        painter.clearSquare(x, y);
    }

    public void moveDown() {
        this.y += 1;
    }

    public void moveLeft() {
        this.x -= 1;
    }

    public void moveRight() {
        this.x += 1;
    }

    public boolean canMoveDown() {
        return y < Settings.MAX_CELLS_HEIGHT - 1;
    }

    public boolean canMoveLeft() {
        return x > 0;
    }

    public boolean canMoveRight() {
        return x < Settings.MAX_CELLS_WIDTH - 1;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
