package edu.csub.startracker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * GameView class for Star Tracker
 * @author Diana Balderas
 * @version 1.0
 */
public class GameView extends SurfaceView implements Runnable {
    private final Background background1;
    private final Background background2;
    private boolean isPlaying = true;
    private Thread thread;
    private int touchX, touchY;
    private ArrayList<Laser> lasers;
    private ArrayList<GameObject> enemies;
    private GameActivity gameActivity;

    private final Player player;

    private EnemySpawner spawner;
    private final float screenWidth, screenHeight;
    private Paint textPaint = new Paint();
    private Paint highScorePaint = new Paint();
    private HighScore highScore = HighScore.getInstance();


    /**
     * The layout of the game is implemented with images for the background
     * @param context the items used to design the game
     * @param screenX adjusting the screen of the game
     * @param screenY adjusting the screen of the gamr
     */
    public GameView(GameActivity context, int screenX, int screenY) {
        super(context);


        Resources res = getResources();
        screenWidth = res.getDisplayMetrics().widthPixels;
        screenHeight = res.getDisplayMetrics().heightPixels;
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(screenWidth * 0.1f);

        highScorePaint.setColor(Color.WHITE);
        highScorePaint.setTextSize(screenWidth * 0.04f);

        background1 = new Background(screenX, screenY, res);
        background2 = new Background(screenX, screenY, res);
        background2.setY(screenY);

        player = new Player(res);
        spawner = new EnemySpawner(res);

        lasers = player.getLasers();
        enemies = spawner.getEnemies();

        gameActivity = context;


    }

    /**
     * When the player touches the game, motion is implemented
     * @param event the action of the game when it is touched
     * @return the touch feature starts the game
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        touchX = (int)event.getX();
        touchY = (int)event.getY();
        return true;
    }

    /**
     * Runs the game when it is played by the player
     */
    @Override
    public void run() {
        while(isPlaying) {
            update();
            draw();
            sleep();
        }
    }

    /**
     * Updates the game when it is played by the player
     */
    private void update() {
        background1.update();
        background2.update();
        player.updateTouch(touchX, touchY);
        player.update();
        spawner.update();
        checkAllCollisions();
        checkEnemiesOffScreen();


    }

    /**
     * Check the amount of enemies that are off the screen
     */
    private void checkEnemiesOffScreen() {
        for(GameObject go : enemies) {
            if(go.getY() > screenHeight) {
                player.takeDamage(100);
                go.takeDamage(100);
                gameActivity.gameOver();
            }
        }
    }

    /**
     * Check the amount of collisions that are set on the sceeen
     */
    private void checkAllCollisions() {
        for(Laser laser : lasers) {
            for(GameObject go : enemies) {
                if(checkCollision(laser, go)) {
                    laser.takeDamage(100);
                    go.takeDamage(25);
                    highScore.addScore(25);

                }
            }
        }

        for(GameObject go : enemies) {
            if(checkCollision(player, go)) {
                player.takeDamage(100);
                go.takeDamage(100);
                gameActivity.gameOver();
            }
        }
    }

    /**
     * Check for collisions based on width and height
     * @param g1 object that is used to check for collisions
     * @param g2 object that is used to check for collisions
     * @return the position where the collision occurs
     */
    private boolean checkCollision(GameObject g1, GameObject g2) {
        return g1.getX() < g2.getX() + g2.getWidth() &&
                g1.getX() + g1.getWidth() > g2.getX() &&
                g1.getY() < g2.getY() + g2.getHeight() &&
                g1.getY() + g1.getHeight() > g2.getY();

    }

    /**
     * Draws the game's design when it is played by the player
     */
    private void draw() {
        if(getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();

            background1.draw(canvas);
            background2.draw(canvas);

            if(!player.isAlive()) {
                canvas.drawText("GAME OVER", screenWidth / 4f, screenHeight / 2f, textPaint);
            }

            canvas.drawText(String.format("Score: %s",
                    highScore.getCurScore()), screenWidth * 0.02f,
                    screenHeight * 0.06f, highScorePaint);

            player.draw(canvas);
            spawner.draw(canvas);

            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    /**
     * Sleeps the game when it is played by the player
     */
    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pauses the game when it is played by the player
     */
    public void pause() {
        isPlaying = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Resumes the game when it is played by the player
     */
    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }
}
