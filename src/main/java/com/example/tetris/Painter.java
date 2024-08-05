package com.example.tetris;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Painter {
    private double sizeX;
    private double sizeY;
    private int cellsX;
    private int cellsY;
    private GraphicsContext context;
    private static Painter instance;
    private static Map<String, Painter> painterMap = new HashMap<>();

    private Painter(int cellsX, int cellsY, GraphicsContext context) {
        sizeX = context.getCanvas().getWidth();
        sizeY = context.getCanvas().getHeight();
        this.cellsX = cellsX;
        this.cellsY = cellsY;
        this.context = context;
        this.clearAll();
    }

    public void drawSquare(int x, int y) {
        context.setFill(Settings.FIGURE_COLOR);
        context.fillRect(sizeX/cellsX * x, sizeY/cellsY * y, sizeX/cellsX, sizeY/cellsY);
        context.setFill(Settings.GROUND_COLOR);
        context.fillRect(sizeX/cellsX * (x + 0.1), sizeY/cellsY * (y + 0.1), sizeX/cellsX * 0.8, sizeY/cellsY * 0.8);
        context.setFill(Settings.FIGURE_COLOR);
        context.fillRect(sizeX/cellsX * (x + 0.2), sizeY/cellsY * (y + 0.2), sizeX/cellsX * 0.6, sizeY/cellsY * 0.6);
    }

    public void clearSquare(int x, int y) {
        context.setFill(Settings.CELL_COLOR);
        context.fillRect(sizeX/cellsX * x, sizeY/cellsY * y, sizeX/cellsX, sizeY/cellsY);
        context.setFill(Settings.GROUND_COLOR);
        context.fillRect(sizeX/cellsX * (x + 0.1), sizeY/cellsY * (y + 0.1), sizeX/cellsX * 0.8, sizeY/cellsY * 0.8);
        context.setFill(Settings.CELL_COLOR);
        context.fillRect(sizeX/cellsX * (x + 0.2), sizeY/cellsY * (y + 0.2), sizeX/cellsX * 0.6, sizeY/cellsY * 0.6);
    }

    public void clearAll() {
        for (int y = 0; y < Settings.MAX_CELLS_HEIGHT; ++y) {
            for (int x = 0; x < Settings.MAX_CELLS_WIDTH; ++x) {
                clearSquare(x, y);
            }
        }
    }

    public void drawLine(int line) {
        for (int x = 0; x < Settings.MAX_CELLS_WIDTH; ++x) {
            drawSquare(x, line);
        }
    }

    public void clearLine(int line) {
        for (int x = 0; x < Settings.MAX_CELLS_WIDTH; ++x) {
            clearSquare(x, line);
        }
    }

    public static void registerGraphicsContext(GraphicsContext context){
        instance = new Painter(Settings.MAX_CELLS_WIDTH, Settings.MAX_CELLS_HEIGHT, context);
    }

    public static Painter getInstance() {
        return instance;
    }

    public static void registerGraphicsContext(GraphicsContext context, String name, int x, int y) {
        painterMap.put(name, new Painter(x, y, context));
    }

    public static Painter getInstance(String name) {
        return painterMap.get(name);
    }
}
