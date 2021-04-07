package edu.csub.startracker;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Laser class for Star Tracker
 * @author Diana Balderas
 * @version 1.0
 */
public class Laser {
    private float x, y;
    private Bitmap laser;
    private float dpi;
    private Paint paint = new Paint();

    /**
     * Resources from mipmap are used in the game activity
     * @param res the resources that make up the design of the game
     */
    public Laser(Resources res) {
        laser = BitmapFactory.decodeResource(res, R.mipmap.bullet);
        dpi = res.getDisplayMetrics().densityDpi;
    }

    /**
     * The screen is given a fixed height based on the player's movement
     * @return representing the height of the player
     */
    public boolean isOnScreen() {
        return !(y < getHeight());
    }

    /**
     * To update the application's layout
     */
    public void update() {
        y -= 0.1f * dpi;
    }

    /**
     * The layout of the game is based on the designs used
     * @param canvas implements the images and designs in the game
     */
    public void draw(Canvas canvas) {
        canvas.drawBitmap(laser, this.x, this.y, this.paint);
    }

    /**
     * Get the width of the player's position
     * @return representing the position of the lasers
     */
    public float getMidX() {
        return laser.getWidth() / 2f;
    }

    /**
     * Get the height of the player's position
     * @return representing the position of the lasers
     */
    public float getHeight() {
        return laser.getHeight();
    }

    /**
     * Gets the x position of the player
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
     * Gets the y position of the player
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
}
