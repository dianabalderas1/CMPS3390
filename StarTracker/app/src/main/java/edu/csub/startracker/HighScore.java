package edu.csub.startracker;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * HighScore class for Star Tracker
 * @author Diana Balderas
 * @version 1.0
 */
public final class HighScore {
    private static final HighScore INSTANCE = new HighScore();
    private int curScore = 0;
    private int highScore = 0;
    private String name = "Player 1";
    private FirebaseFirestore db;


    /**
     * Database to store information to Firebase
     */
    private HighScore() {
        db = FirebaseFirestore.getInstance();
    }

    /**
     * Get the instance high score of the player
     * @return the instance of the player
     */
    public static HighScore getInstance() {
        return INSTANCE;
    }

    /**
     * The current score and overall score are added to get the highest score
     * @param score the current points that the player has
     */
    public void addScore(int score) {
        curScore += score;
        if(curScore > highScore) {
            highScore = curScore;
        }
    }

    /**
     * Get the current score of the player
     * @return current score of the player
     */
    public int getCurScore() {
        return curScore;
    }

    /**
     * Get the highest score of the player
     * @return highest score of the player
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * Resets the current score of the player when user plays each time
     */
    public void resetCurScore() {
        curScore = 0;
    }

    /**
     * Sets the player name on the screen
     * @param playerName the name of player is shown in the game
     */
    public void setPlayerName(String playerName) {
        name = playerName;
    }

    /**
     * Get the player name on the screen
     * @return the name of player is shown in the game
     */
    public String getPlayerName() {
        return name;
    }

    /**
     * Get the highest scores from all players in the game
     * @param howMany the amount of players in the game
     * @param highScores the highest scores from players are selected to be listed
     * @param context the display of how all the top players in the game
     */
    public void getHighScores(int howMany, ListView highScores, Context context) {
        ArrayList<String> topScores = new ArrayList<>();

        db.collection("HighScore")
                .orderBy("score", Query.Direction.DESCENDING)
                .limit(howMany)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            for(QueryDocumentSnapshot doc : task.getResult()) {
                                String tmpString = String.format("%s: %s", doc.getId(),
                                    doc.get("score"));
                                 topScores.add(tmpString);
                                Log.d("SCORE", tmpString);
                            }

                            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(context,
                                    android.R.layout.simple_list_item_1, topScores);
                            highScores.setAdapter(itemsAdapter);
                        }
                    }
                });
    }

    /**
     * The highest scores of the top players are posted in a list
     */
    public void postHighScore() {
        Map<String, Integer> hs = new HashMap<>();
        hs.put("score", highScore);

        db.collection("HighScore").document(name)
                .set(hs)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("data", name + "'s score was set");

                    }
                });

    }
}
