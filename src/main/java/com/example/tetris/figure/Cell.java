package com.example.tetris.figure;

import com.example.tetris.Painter;
import com.example.tetris.Settings;
import javafx.scene.canvas.GraphicsContext;

import java.util.Objects;

public class Cell {
    private int x;
    private int y;
    private Painter painter;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.painter = Painter.getInstance();
    }

    public Cell(Cell cell) {
        this.x = cell.x;
        this.y = cell.y;
        this.painter = cell.painter;
    }

    public void draw() {
        painter.drawSquare(x, y);
    }

    public void clear(){ painter.clearSquare(x, y); }

    public void moveDown() {
        this.y += 1;
    }

    public void moveUp() {this.y -= 1; }

    public void moveDown(int distance) {
        this.y += distance;
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

    public boolean canMoveUp() { return y > 0; }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
