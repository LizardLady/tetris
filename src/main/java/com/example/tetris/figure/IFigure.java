package com.example.tetris.figure;

import com.example.tetris.Settings;
import javafx.scene.canvas.GraphicsContext;

public class IFigure extends Figure{
    private int position = 1;
    public IFigure(int x, int y) {
        cellList.add(new Cell(x, y));
        cellList.add(new Cell(x+1, y));
        cellList.add(new Cell(x+2, y));
        cellList.add(new Cell(x+3, y));
    }


    @Override
    public boolean rotate() {
        if (this.position == 1 && this.canRotate()) {
            cellList.get(0).setX(cellList.get(0).getX() + 1);
            cellList.get(0).setY(cellList.get(0).getY() - 1);
            cellList.get(2).setX(cellList.get(2).getX() - 1);
            cellList.get(2).setY(cellList.get(2).getY() + 1);
            cellList.get(3).setX(cellList.get(3).getX() - 2);
            cellList.get(3).setY(cellList.get(3).getY() + 2);
            this.position = 2;
            this.fitInsideBoard();
            return true;
        } else if (this.position == 2) {
            cellList.get(0).setX(cellList.get(0).getX() - 1);
            cellList.get(0).setY(cellList.get(0).getY() + 1);
            cellList.get(2).setX(cellList.get(2).getX() + 1);
            cellList.get(2).setY(cellList.get(2).getY() - 1);
            cellList.get(3).setX(cellList.get(3).getX() + 2);
            cellList.get(3).setY(cellList.get(3).getY() - 2);
            this.position = 1;
            this.fitInsideBoard();
            return true;
        } else return false;
    }

    private boolean canRotate() {
        if (position == 1) {
            return cellList.get(3).getY() + 2 < Settings.MAX_CELLS_HEIGHT;
        }
        return true;
    }
}
