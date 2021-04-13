package edu.csub.startracker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;

/**
 * GameActivity class for Star Tracker
 * @author Diana Balderas
 * @version 1.0
 */
public class GameActivity extends AppCompatActivity {
    private GameView gameView;

    /**
     * The game is saved based on the player's activity
     * @param savedInstanceState representing the save data from the game
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        gameView = new GameView(this, point.x, point.y);

        setContentView(gameView);
    }

    /**
     * Identifies when the game is over, based ont player
     */
    public void gameOver() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 6000);
    }

    /**
     * Pauses the game when it is played by the player
     */
    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    /**
     * Resumes the game when it is played by the player
     */
    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
}