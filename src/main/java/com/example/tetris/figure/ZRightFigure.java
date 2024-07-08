package com.example.tetris.figure;

import javafx.scene.canvas.GraphicsContext;

public class ZRightFigure extends Figure{
    public ZRightFigure(int x, int y, GraphicsContext context) {
        super(context);
        cellList.add(new Cell(x, y+1, context));
        cellList.add(new Cell(x+1, y+1, context));
        cellList.add(new Cell(x+1, y, context));
        cellList.add(new Cell(x+2, y, context));
    }

    @Override
    public boolean rotate() {
        return false;
    }
}
