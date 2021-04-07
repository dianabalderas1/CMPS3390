package edu.csub.startracker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceView;

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

    private final Player player;

    /**
     * The layout of the game is implemented with images for the background
     * @param context the items used to design the game
     * @param screenX adjusting the screen of the game
     * @param screenY adjusting the screen of the gamr
     */
    public GameView(Context context, int screenX, int screenY) {
        super(context);

        Resources res = getResources();

        background1 = new Background(screenX, screenY, res);
        background2 = new Background(screenX, screenY, res);
        background2.setY(screenY);

        player = new Player(res);

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
        player.update(touchX, touchY);
    }

    /**
     * Draws the game's design when it is played by the player
     */
    private void draw() {
        if(getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();

            background1.draw(canvas);
            background2.draw(canvas);
            player.draw(canvas);

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
