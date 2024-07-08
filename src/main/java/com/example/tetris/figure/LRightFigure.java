package com.example.tetris.figure;

import javafx.scene.canvas.GraphicsContext;

public class LRightFigure extends Figure{
    public LRightFigure(int x, int y, GraphicsContext context) {
        super(context);
        cellList.add(new Cell(x, y+1, context));
        cellList.add(new Cell(x+1, y+1, context));
        cellList.add(new Cell(x+2, y+1, context));
        cellList.add(new Cell(x+3, y, context));
    }

    @Override
    public boolean rotate() {
        return false;
    }
}
