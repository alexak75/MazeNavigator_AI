package com.ramapo.akuhles.mazenavigator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Alex on 3/4/2015.
 */
public class Game extends Activity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();    // Get the intent extras
        Maze maze = (Maze)extras.get("maze");  // Retrieve the maze from intent extras
        GameView view = new GameView(this,maze);
        setContentView(view);
    }
}
