package edu.csub.startracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

/**
 * Main Driver class for Star Tracker
 * @author Diana Balderas
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The activity main is the first screen on the application
     * @param savedInstanceState the layout is saved as the introduction of the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * Once button clicked, it will take the user to the main application
     * @param view the layout of the application in both the main activity and game
     */
    public void onPlayButtonClicked(View view) {
        startActivity(new Intent(MainActivity.this, GameActivity.class));
    }
}