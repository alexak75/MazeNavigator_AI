package com.ramapo.akuhles.mazenavigator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import java.util.Random;

/*******************************************************************
 * Name:  Alexander Kuhles                                         *
 * Project:  Project 2                                             *
 * Class:  Artificial Intelligence                                 *
 * Date:  March 10, 2015                                           *
 ********************************************************************/
public class GameView extends View {

    private int width, height, lineWidth;
    private int mazeSize, mazeSizeX, mazeSizeY;
    private int screenWidth, screenHeight;
    float cellWidth, cellHeight;
    float totalCellWidth, totalCellHeight;
    private int mazeFinishX, mazeFinishY;
    private Maze maze;
    private Activity context;
    private Paint line, red, background;

    private static final String TAG = GameView.class.getSimpleName();

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public GameView(Context context, Maze maze) {
            super(context);
        this.context = (Activity)context;
        this.maze = MazeCreator.getMaze(1);
        mazeSize = 8;                      // Hard-coded for now
        maze.setMazeWidth(mazeSize);       // Set total maze width
        maze.setMazeHeight(mazeSize);      // Set total maze height
        mazeFinishX = maze.getFinalX();    // Sets the end point of the maze
        mazeFinishY = maze.getFinalY();
        mazeSizeX = maze.getMazeWidth();   // Sets the width variable of the maze
        mazeSizeY = maze.getMazeHeight();  // Sets the height variable of the maze
        line = new Paint();
        line.setColor(getResources().getColor(R.color.line));
        red = new Paint();
        red.setColor(getResources().getColor(R.color.position));
        background = new Paint();
        background.setColor(getResources().getColor(R.color.maze_bg));
        setFocusable(true);                // Allows the maze to be focusable/interactive
        this.setFocusableInTouchMode(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Fill in the background
        canvas.drawRect(0, 0, width, height, background);
        boolean[][] hLines = maze.getHorizontalLines();
        boolean[][] vLines = maze.getVerticalLines();
        // Iterate over the boolean arrays to draw walls
        for(int i = 0; i < mazeSizeX; i++) {
            for(int j = 0; j < mazeSizeY; j++){
                float x = j * totalCellWidth;
                float y = i * totalCellHeight;
                if(j < mazeSizeX - 1 && vLines[i][j]) {
                    // Draw a vertical line
                    canvas.drawLine(x + cellWidth,   // start X
                            y,               // start Y
                            x + cellWidth,   // stop X
                            y + cellHeight,  // stop Y
                            line);
                }
                if(i < mazeSizeY - 1 && hLines[i][j]) {
                    // Draw a horizontal line
                    canvas.drawLine(x,               // startX
                            y + cellHeight,  // startY
                            x + cellWidth,   // stopX
                            y + cellHeight,  // stopY
                            line);
                }
            }
        }
        int currentX = maze.getCurrentX(),currentY = maze.getCurrentY();
        // Draw the shaded cell
        canvas.drawRect((currentX * totalCellWidth + totalCellWidth/6),      // startX
                (currentY * totalCellHeight + totalCellHeight/6),               // startY
                (currentX * totalCellWidth - totalCellWidth/6) + (cellWidth),    // endX
                (currentY * totalCellHeight - totalCellHeight/6) + (cellHeight),  // endY
                red);
        // Draw the finishing point indicator
        canvas.drawText("F",
                (mazeFinishX * totalCellWidth) + (cellWidth * 0.25f),
                (mazeFinishY * totalCellHeight) + (cellHeight * 0.75f),
                red);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        screenWidth = w;
        screenHeight = h;
        width = (w < h)?w:h;    // Check whether the width or height of the screen is smaller and assign it to width
        height = width;         // Square maze for now
        width -= 40;            // Pads the spacing a bit to prevent clipping with the keyboard
        height -= 40;
        lineWidth = 1;          // 1 pixel wide walls for now
        cellWidth = (width - ((float)mazeSizeX*lineWidth)) / mazeSizeX;     // Resize the maze on creation/screen rotation
        totalCellWidth = cellWidth+lineWidth;
        cellHeight = (height - ((float)mazeSizeY*lineWidth)) / mazeSizeY;
        totalCellHeight = cellHeight+lineWidth;
        red.setTextSize(cellHeight*0.75f);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent evt) {
        boolean moved = false; // Is set to true if the player moves
        switch(keyCode) {
            case KeyEvent.KEYCODE_W:
                moved = maze.move(Maze.UP);
                break;
            case KeyEvent.KEYCODE_S:
                moved = maze.move(Maze.DOWN);
                break;
            case KeyEvent.KEYCODE_D:
                moved = maze.move(Maze.RIGHT);
                break;
            case KeyEvent.KEYCODE_A:
                moved = maze.move(Maze.LEFT);
                break;
            default:
                return super.onKeyDown(keyCode,evt);
        }
        if(moved) {
            // The ball was moved so we'll redraw the view
            invalidate();
            if(maze.isGameComplete()) {
                // Game completed - displays the new activity
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(context.getText(R.string.finished_title));
                LayoutInflater inflater = context.getLayoutInflater();
                View view = inflater.inflate(R.layout.finish, null);
                builder.setView(view);
                View closeButton = view.findViewById(R.id.closeGame);
                closeButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View clicked) {
                        if(clicked.getId() == R.id.closeGame) {
                            context.finish();
                        }
                    }
                });
                AlertDialog finishDialog = builder.create();
                finishDialog.show();
            }
        }
        return true;
    }
}
