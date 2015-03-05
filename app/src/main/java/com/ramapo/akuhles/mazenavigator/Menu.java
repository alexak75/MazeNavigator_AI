package com.ramapo.akuhles.mazenavigator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Alex on 3/4/2015.;
 */
public class Menu extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button newGame = (Button)findViewById(R.id.bNew);
        Button exit = (Button)findViewById(R.id.bExit);
        newGame.setOnClickListener(this);
        exit.setOnClickListener(this);
    }

    public void onClick(View view) {
        //check which button was clicked with its id
        switch(view.getId()) {
            case R.id.bExit:
                finish();
                break;
            case R.id.bNew:
                String[] levels = {"Maze 1", "Maze 2", "Maze 3"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getString(R.string.levelSelect));
                builder.setItems(levels, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        Intent game = new Intent(Menu.this,Game.class);  //create an Intent to launch the Game Activity
                        Maze maze = MazeCreator.getMaze(item+1);         //use helper class for creating the Maze
                        game.putExtra("maze", maze);                     //add the maze to the intent which we'll retrieve in the Maze Activity
                        startActivity(game);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
        }
    }
}