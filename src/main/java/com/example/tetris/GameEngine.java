package com.example.tetris;

import com.example.tetris.figure.Cell;
import com.example.tetris.figure.Figure;
import javafx.beans.property.StringProperty;

import java.util.Collections;
import java.util.List;

public class GameEngine {
    private Board board = new Board();
    private boolean game = true;
    private FigureFactory figureFactory = new FigureFactoryImpl();
    private ScoreHandler scoreHandler;
    private boolean skipTick = false;
    private Figure nextFigure = figureFactory.getFigure();
    private Painter nextFigurePainter = Painter.getInstance("nextFigure");

    public GameEngine(StringProperty label) {
        board.setCurrentFigure(figureFactory.getFigure());
        board.getCurrentFigure().draw();
        scoreHandler = new ScoreHandler(label);
        drawNextFigure();
    }

    public Boolean getSkipTick() {
        return skipTick;
    }

    public void setSkipTick(Boolean skipTick) {
        this.skipTick = skipTick;
    }

    public boolean isGame() {
        return game;
    }

    public void setGame(boolean game) {
        this.game = game;
    }

    public synchronized void moveLeft() {
        if(board.getCurrentFigure() == null) {
            return;
        }
        board.getCurrentFigure().clear();
        board.getCurrentFigure().moveLeft();
        if (board.hasCollisions()) {
            board.getCurrentFigure().moveRight();
        }
        board.getCurrentFigure().draw();
    }
    public synchronized void moveRight() {
        if(board.getCurrentFigure() == null) {
            return;
        }
        board.getCurrentFigure().clear();
        board.getCurrentFigure().moveRight();
        if (board.hasCollisions()) {
            board.getCurrentFigure().moveLeft();
        }
        board.getCurrentFigure().draw();
    }

    public synchronized boolean moveDown() {
        if(board.getCurrentFigure() == null) {
            return true;
        }
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

    public synchronized void rotate() {
        if(board.getCurrentFigure() == null) {
            return;
        }
        board.getCurrentFigure().clear();
        Figure copyFigure = board.getCurrentFigure().clone();
        board.getCurrentFigure().rotate();
        if (board.hasCollisions()) {
            board.setCurrentFigure(copyFigure);
        }
        board.getCurrentFigure().draw();
    }

    public void dropDown() {
        Figure currentFigure = this.board.getCurrentFigure();
        while (currentFigure == this.board.getCurrentFigure() && !this.skipTick && this.isGame()) {
            tick();
        }
    }

    private void drawNextFigure(){
        nextFigurePainter.clearAll();
        Cell leftestOFHighs = new Cell(Settings.MAX_CELLS_WIDTH, Settings.MAX_CELLS_HEIGHT);
        Cell highestOfLefts = new Cell(Settings.MAX_CELLS_WIDTH, Settings.MAX_CELLS_HEIGHT);
        for (Cell cell : nextFigure.getCellList()) {
            if (cell.getY() < leftestOFHighs.getY()) {
                leftestOFHighs.setY(cell.getY());
                leftestOFHighs.setX(cell.getX());
            }
            if (cell.getY() == leftestOFHighs.getY() && cell.getX() < leftestOFHighs.getX()) {
                leftestOFHighs.setX(cell.getX());
                leftestOFHighs.setY(cell.getY());
            }
            if (cell.getX() < highestOfLefts.getX()) {
                highestOfLefts.setX(cell.getX());
                highestOfLefts.setY(cell.getY());
            }
            if (cell.getX() == highestOfLefts.getX() && cell.getY() < highestOfLefts.getY()) {
                highestOfLefts.setY(cell.getY());
                highestOfLefts.setX(cell.getX());
            }
        }

        for (Cell cell : nextFigure.getCellList()) {
            nextFigurePainter.drawSquare(cell.getX() - highestOfLefts.getX(), cell.getY() - leftestOFHighs.getY());
        }
    }


    public synchronized boolean tick() {
        if (skipTick) {
            return true;
        }


        if (!moveDown()) {
            board.freezeFigure();
            List<Integer> fullLines = board.getFullLines();
            scoreHandler.handler(fullLines.size());
            Thread thread = new Thread(this::tickLogic);
            thread.start();

        }
        game = !board.isFull();
        return game;
    }

    public void tickLogic() {
        this.skipTick = true;
        List<Integer> fullLines = board.getFullLines();
        try{
            for (int i = 0; i < 3; ++i) {
                fullLines.forEach(line -> Painter.getInstance().clearLine(line));
                Thread.sleep(100);
                fullLines.forEach(line -> Painter.getInstance().drawLine(line));
                Thread.sleep(100);
            }
        } catch (InterruptedException interruptedException) {

        }
        board.clearAll();
        fullLines.forEach(line -> board.removeLine(line));
        Collections.sort(fullLines);
        fullLines.forEach(line -> board.moveDown(1, line));
        board.drawAll();
        board.setCurrentFigure(this.nextFigure);
        this.nextFigure = figureFactory.getFigure();
        drawNextFigure();
        this.skipTick = false;
    }
}
