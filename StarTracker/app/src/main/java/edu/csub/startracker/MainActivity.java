package edu.csub.startracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Main Driver class for Star Tracker
 * @author Diana Balderas
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    private HighScore highScore = HighScore.getInstance();

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
     * Once the game is over, it resume sto the beginning of the game
     */
    @Override
    protected void onResume() {
        super.onResume();
        getTopScores(100);
        TextView tvHighScore = findViewById(R.id.tvHighScore);
        EditText etPlayerName = findViewById(R.id.etPlayerName);
        etPlayerName.setText(highScore.getPlayerName());
        tvHighScore.setText(String.format("High Score: %s" , highScore.getHighScore()));
        if(highScore.getHighScore() != 0 && highScore.getHighScore() == highScore.getCurScore()) {
            highScore.postHighScore();
        }
    }

    /**
     * Displays the top players of the game
     * @param howMany represent how many players have a top score
     */
    private void getTopScores(int howMany) {
        ListView highScores = findViewById(R.id.lvTopScores);
        highScore.getHighScores(howMany, highScores,this);
    }

    /**
     * Once button clicked, it will take the user to the main application
     * @param view the layout of the application in both the main activity and game
     */
    public void onPlayButtonClicked(View view) {
        highScore.resetCurScore();
        EditText etPlayerName = findViewById(R.id.etPlayerName);
        highScore.setPlayerName(etPlayerName.getText().toString());
        startActivity(new Intent(MainActivity.this, GameActivity.class));
    }
}