package com.example.tetris;

import com.example.tetris.figure.*;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class FigureFactoryImpl implements FigureFactory{
    private List<Supplier<Figure>> constructors = List.of(
            () -> new SquareFigure((Settings.MAX_CELLS_WIDTH - 2) / 2, 0),
            () -> new IFigure((Settings.MAX_CELLS_WIDTH - 4) / 2, 0),
            () -> new TFigure((Settings.MAX_CELLS_WIDTH - 3) / 2, 0),
            () -> new LLeftFigure((Settings.MAX_CELLS_WIDTH - 3) / 2, 0),
            () -> new LRightFigure((Settings.MAX_CELLS_WIDTH - 3) / 2, 0),
            () -> new ZLeftFigure((Settings.MAX_CELLS_WIDTH - 3) / 2, 0),
            () -> new ZRightFigure((Settings.MAX_CELLS_WIDTH - 3) / 2, 0)
    );

    @Override
    public Figure getFigure() {
        Random random = new SecureRandom();
        return constructors.get(random.nextInt(constructors.size())).get();
    }
}
