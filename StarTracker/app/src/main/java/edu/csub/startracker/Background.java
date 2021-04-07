package edu.csub.startracker;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Background class for Star Tracker
 * @author Diana Balderas
 * @version 1.0
 */
public class Background {
    private Bitmap background;
    private int screenX, screenY;

    private Paint paint = new Paint();
    private float dpi;

    private float x = 0;
    private float y = 0;

    /**
     * The background is adjusted based on the player's position
     * @param screenX the position of the game
     * @param screenY the position of the game
     * @param res the resources implemented in the game
     */
    public Background(int screenX, int screenY, Resources res) {
        this.screenX = screenX;
        this.screenY = screenY;
        this.background = BitmapFactory.decodeResource(res, R.mipmap.background);
        this.background = Bitmap.createScaledBitmap(this.background, screenX, screenY, false);
        this.dpi = res.getDisplayMetrics().densityDpi;
    }

    /**
     * Get the x position of the player
     * @return the position is fixed in the game
     */
    public float getX() {
        return x;
    }

    /**
     * Sets the x position of the player
     * @param x the position is fixed in the game
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Get the y position of the player
     * @return the position is fixed in the game
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the y position of the player
     * @param y the position is fixed in the game
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Updates the screen of the game
     */
    public void update() {
        this.y += 0.006f * dpi;

        if(this.y > screenY) {
            this.y = -screenY;
        }
    }

    /**
     * Draws the screen of the game
     * @param canvas template to layout the game's design
     */
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.background, this.x, this.y, paint);
    }
}
