package com.example.tetris;

import javafx.scene.canvas.GraphicsContext;

public class Painter {
    private double sizeX;
    private double sizeY;
    private int cellsX;
    private int cellsY;
    private GraphicsContext context;

    public Painter(int cellsX, int cellsY, GraphicsContext context) {
        sizeX = context.getCanvas().getWidth();
        sizeY = context.getCanvas().getHeight();
        this.cellsX = cellsX;
        this.cellsY = cellsY;
        this.context = context;
    }

    public void drawSquare(int x, int y) {
        context.fillRect(sizeX/cellsX * x, sizeY/cellsY * y, sizeX/cellsX, sizeY/cellsY);
    }

    public void clearSquare(int x, int y) {
        context.clearRect(sizeX/cellsX * x, sizeY/cellsY * y, sizeX/cellsX, sizeY/cellsY);
    }
}
