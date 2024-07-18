package com.example.tetris;

public class DaemonGameThread extends Thread{
    private GameEngine gameEngine;

    public DaemonGameThread(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void run() {
        while (gameEngine.isGame() && gameEngine.tick()) {
            try {
                Thread.sleep((long) (1000 / Settings.SPEED));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
