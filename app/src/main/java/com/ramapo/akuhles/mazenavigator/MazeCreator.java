package com.ramapo.akuhles.mazenavigator;

import java.util.Random;

/*******************************************************************
 * Name:  Alexander Kuhles                                         *
 * Project:  Project 2                                             *
 * Class:  Artificial Intelligence                                 *
 * Date:  March 10, 2015                                           *
 ********************************************************************/
public class MazeCreator {

    // Params: a single integer that determines the maze to be returned
    public static Maze getMaze(int mazeNo) {
        Random rand = new Random();
        int startPos = rand.nextInt(8);
        Maze maze = null;
        if(mazeNo == 1) {
            maze = new Maze();
            boolean[][] vLines = new boolean[][]{
                    {true ,false,false,false,true ,false,false},
                    {true ,false,false,true ,false,true ,true },
                    {false,true ,false,false,true ,false,false},
                    {false,true ,true ,false,false,false,true },
                    {true ,false,false,false,true ,true ,false},
                    {false,true ,false,false,true ,false,false},
                    {false,true ,true ,true ,true ,true ,false},
                    {false,false,false,true ,false,false,false}
            };
            boolean[][] hLines = new boolean[][]{
                    {false,false,true ,true ,false,false,true ,false},
                    {false,false,true ,true ,false,true ,false,false},
                    {true ,true ,false,true ,true ,false,true ,true },
                    {false,false,true ,false,true ,true ,false,false},
                    {false,true ,true ,true ,true ,false,true ,true },
                    {true ,false,false,true ,false,false,true ,false},
                    {false,true ,false,false,false,true ,false,true }
            };
            maze.setVerticalLines(vLines);
            maze.setHorizontalLines(hLines);
            maze.setStartPosition(0, startPos);
            maze.setFinalPosition(7, 7);
        }
        // Other mazes go here (2, 3, etc.)
        return maze;
    }

}
