package com.ramapo.akuhles.mazenavigator;

import java.io.Serializable;

/*******************************************************************
 * Name:  Alexander Kuhles                                         *
 * Project:  Project 2                                             *
 * Class:  Artificial Intelligence                                 *
 * Date:  March 10, 2015                                           *
 ********************************************************************/
public class Maze implements Serializable {

    //private static final long serialVersionUID = 1L;

    public static final int UP = 0, DOWN = 1, RIGHT = 2, LEFT = 3;

    private boolean[][] verticalLines;
    private boolean[][] horizontalLines;
    private int sizeX, sizeY;         // Stores the width and height of the maze
    private int startX, startY;
    private int currentX, currentY;   // Stores the current location of the player
    private int finalX, finalY;       // Stores the finishing point of the maze
    private boolean gameComplete;

    //setters and getters
    public boolean isGameComplete() {
        return gameComplete;
    }
    public boolean[][] getHorizontalLines() {
        return horizontalLines;
    }
    public boolean[][] getVerticalLines() {
        return verticalLines;
    }
    public void setStartX(int startX) {
        currentX = startX;
    }
    public void setStartY(int startY) {
        currentY = startY;
    }
    public void setMazeWidth(int sizeX) {
        this.sizeX = sizeX;
    }
    public void setMazeHeight(int sizeY) {
        this.sizeY = sizeY;
    }
    public int getMazeWidth() {
        return sizeX;
    }
    public int getMazeHeight() {
        return sizeY;
    }
    public void setHorizontalLines(boolean[][] horizontalLines) {
        this.horizontalLines = horizontalLines;
    }
    public void setVerticalLines(boolean[][] verticalLines) {
        this.verticalLines = verticalLines;
    }
    public int getCurrentX() {
        return currentX;
    }
    public int getCurrentY() {
        return currentY;
    }
    public void setStartPosition(int x, int y) {
        currentX = x;
        currentY = y;
    }
    public void setFinalPosition(int x, int y) {
        finalX = x;
        finalY = y;
    }
    public int getFinalX() {
        return finalX;
    }
    public int getFinalY() {
        return finalY;
    }

    // Params: an integer that defines the direction that the player moves in
    public boolean move(int direction) {
        boolean moved = false;
        if(direction == UP) {
            if(currentY != 0 && !horizontalLines[currentY-1][currentX]) {
                currentY--;
                moved = true;
            }
        }
        if(direction == DOWN) {
            if(currentY != sizeY-1 && !horizontalLines[currentY][currentX]) {
                currentY++;
                moved = true;
            }
        }
        if(direction == RIGHT) {
            if(currentX != sizeX-1 && !verticalLines[currentY][currentX]) {
                currentX++;
                moved = true;
            }
        }
        if(direction == LEFT) {
            if(currentX != 0 && !verticalLines[currentY][currentX-1]) {
                currentX--;
                moved = true;
            }
        }
        if(moved) {
            if(currentX == finalX && currentY == finalY) {
                gameComplete = true;
            }
        }
        return moved;
    }
}

