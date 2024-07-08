package com.example.tetris.figure;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public abstract class Figure {
    protected List<Cell> cellList = new ArrayList<>();
    protected GraphicsContext context;

    public Figure(GraphicsContext graphicsContext) {
        this.context = graphicsContext;
    }

    public abstract boolean rotate();

    public void draw() {
        for (Cell cell : cellList) {
            cell.draw();
        }
    }

    public void clear() {
        for (Cell cell : cellList) {
            cell.clear();
        }
    }

    public boolean moveDown() {
        if(cellList.stream().allMatch(Cell::canMoveDown)) {
            cellList.forEach(Cell::moveDown);
            return true;
        }
        return false;

//        for (Cell cell : cellList) {
//            if (!cell.canMoveDown())
//                return false;
//        }
//        for (Cell cell : cellList) {
//            cell.moveDown();
//        }
//        return true;
    }

    public boolean moveLeft() {
        if(cellList.stream().allMatch(Cell::canMoveLeft)) {
            cellList.forEach(Cell::moveLeft);
            return true;
        }
        return false;
    }

    public boolean moveRight(){
        if(cellList.stream().allMatch(Cell::canMoveRight)) {
            cellList.forEach(Cell::moveRight);
            return true;
        }
        return false;
    }
}
