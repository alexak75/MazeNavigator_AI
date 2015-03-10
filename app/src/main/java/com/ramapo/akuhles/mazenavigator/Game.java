package com.ramapo.akuhles.mazenavigator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

/**
 * Created by Alex on 3/4/2015.
 */
public class Game extends Activity implements View.OnClickListener {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();    // Get the intent extras
        Maze maze = (Maze)extras.get("maze");  // Retrieve the maze from intent extras
        GameView view = new GameView(this, maze);
        setContentView(view);                  // Set the content view
    }

    public void onClick(View view) {
        // Check which button was clicked with its id
        switch(view.getId()) {
            case R.id.bShowKeyboard:
                if (view.requestFocus()) {
                    // Show keyboard
                    InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
                }
                break;
            case R.id.bSolveMaze:
                // Use a DFS/BFS algorithm to step through the maze until the win condition is met
        }
    }
}
