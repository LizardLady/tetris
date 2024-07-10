package com.example.tetris.figure;

import com.example.tetris.Settings;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Collections;
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

    protected void fitInsideBoard() {
        int maxX = -1;
        Cell maxXCell = null;
        for (Cell cell : cellList) {
            if (cell.getX() > maxX) {
                maxX = cell.getX();
                maxXCell = cell;
            }
        }
        while (maxXCell.getX() > Settings.MAX_CELLS_WIDTH) {
            this.moveLeft();
        }

        int minX = Settings.MAX_CELLS_WIDTH;
        Cell minXCell = null;
        for (Cell cell : cellList) {
            if (cell.getX() < minX) {
                minX = cell.getX();
                minXCell = cell;
            }
        }
        while (minXCell.getX() < 0) {
            this.moveRight();
        }

        int minY = Settings.MAX_CELLS_HEIGHT;
        Cell minYCell = null;
        for (Cell cell : cellList) {
            if (cell.getY() < minY) {
                minY = cell.getX();
                minYCell = cell;
            }
        }
        while (minYCell.getY() < 0) {
            this.moveDown();
        }
    }


}
