package com.example.tetris.figure;

import javafx.scene.canvas.GraphicsContext;

public class SquareFigure extends Figure {

    public SquareFigure(int x, int y) {
        cellList.add(new Cell(x, y));
        cellList.add(new Cell(x+1, y));
        cellList.add(new Cell(x, y+1));
        cellList.add(new Cell(x+1, y+1));
    }

    @Override
    public boolean rotate() {
        return true;
    }
}
