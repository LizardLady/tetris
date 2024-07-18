package com.example.tetris;

import com.example.tetris.figure.Cell;
import com.example.tetris.figure.Figure;
import com.example.tetris.figure.IFigure;
import com.example.tetris.figure.LRightFigure;

import java.util.Collections;
import java.util.List;

public class GameEngine {
    private Board board = new Board();
    private boolean game = true;
    private FigureFactory figureFactory = new FigureFactoryImpl();

    public GameEngine() {
        board.setCurrentFigure(new IFigure(4, 0));
        board.getCurrentFigure().draw();
    }


    public boolean isGame() {
        return game;
    }

    public void setGame(boolean game) {
        this.game = game;
    }

    public void moveLeft() {
        board.getCurrentFigure().clear();
        board.getCurrentFigure().moveLeft();
        if (board.hasCollisions()) {
            board.getCurrentFigure().moveRight();
        }
        board.getCurrentFigure().draw();
    }
    public void moveRight() {
        board.getCurrentFigure().clear();
        board.getCurrentFigure().moveRight();
        if (board.hasCollisions()) {
            board.getCurrentFigure().moveLeft();
        }
        board.getCurrentFigure().draw();
    }

    public boolean moveDown() {
        board.getCurrentFigure().clear();
        boolean isMoved = board.getCurrentFigure().moveDown();
        if (board.hasCollisions()) {
            board.getCurrentFigure().moveUp();
            board.getCurrentFigure().draw();
            return false;
        }
        board.getCurrentFigure().draw();
        return isMoved;
    }

    public void rotate() {
        board.getCurrentFigure().clear();
        Figure copyFigure = board.getCurrentFigure().clone();
        board.getCurrentFigure().rotate();
        if (board.hasCollisions()) {
            board.setCurrentFigure(copyFigure);
        }
        board.getCurrentFigure().draw();
    }

    public boolean tick() {

        if (!moveDown()) {
            board.freezeFigure();
            board.clearAll();
            List<Integer> fullLines = board.getFullLines();
            board.getFullLines().forEach(line -> board.removeLine(line));
            Collections.sort(fullLines);
            fullLines.forEach(line -> board.moveDown(1, line));
            board.drawAll();
            board.setCurrentFigure(figureFactory.getFigure());
        }
        game = !board.isFull();
        return game;
    }
}
