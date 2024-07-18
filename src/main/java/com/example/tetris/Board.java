package com.example.tetris;

import com.example.tetris.figure.Cell;
import com.example.tetris.figure.Figure;

import java.util.*;

public class Board {
    private Figure currentFigure;
    private final List<Cell> boardCells = new LinkedList<>();

    public List<Integer> getFullLines() {
        List<Integer> fullLines = new ArrayList<>();
        List<Integer> cellsInLine = new ArrayList<>();
        for (int i = 0; i < Settings.MAX_CELLS_HEIGHT; ++i) {
            cellsInLine.add(0);
        }
        for (Cell boardCell : boardCells) {
            int y = boardCell.getY();
            cellsInLine.set(y, cellsInLine.get(y) + 1);
            if (cellsInLine.get(y) == Settings.MAX_CELLS_WIDTH) {
                fullLines.add(y);
            }
        }
        return fullLines;
    }

    public void removeLine(int line) {
        Iterator<Cell> iterator = boardCells.iterator();
        while (iterator.hasNext()) {
            Cell cell = iterator.next();
            if (cell.getY() == line) {
                cell.clear();
                iterator.remove();
            }
        }
    }

    public void moveDown(int distance, int fromRow) {
        for (Cell cell : boardCells) {
            if (cell.getY() < fromRow) {
                cell.moveDown(distance);
            }
        }
    }

    public boolean hasCollisions() {
        return boardCells.stream()
               .anyMatch(cell -> currentFigure.getCellList().contains(cell));
    }

    public void freezeFigure() {
        boardCells.addAll(currentFigure.getCellList());
        currentFigure = null;
    }

    public boolean isFull() {
        return boardCells.stream().anyMatch(cell -> cell.getY() == 0);
    }

    public void setCurrentFigure(Figure currentFigure) {
        this.currentFigure = currentFigure;
        currentFigure.draw();
    }

    public Figure getCurrentFigure() {
        return this.currentFigure;
    }

    public void clearAll() {
        boardCells.forEach(Cell::clear);
    }
    public void drawAll() {
        boardCells.forEach(Cell::draw);
    }
}
