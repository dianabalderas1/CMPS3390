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
public class Laser implements GameObject  {
    private float x, y;
    private Bitmap laser;
    private float dpi;
    private Paint paint = new Paint();
    private float health = 100f;
    private final int width, height;

    /**
     * Resources from mipmap are used in the game activity
     * @param res the resources that make up the design of the game
     */
    public Laser(Resources res) {
        laser = BitmapFactory.decodeResource(res, R.mipmap.bullet);
        width = laser.getWidth();
        height = laser.getHeight();
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
    @Override
    public float getHeight() {
        return height;
    }

    /**
     * Determines of the player is alive based on their health
     * @return the health of the player
     */
    @Override
    public boolean isAlive() {
        return health > 0f;
    }

    /**
     * The player receives a health status based on playing the game
     * @return the health of the player
     */
    @Override
    public float getHealth() {
        return health;
    }

    /**
     * The player's health notifies if there were any damage
     * @param damage the injuries on the player's attack
     * @return the health damage the player received
     */
    @Override
    public float takeDamage(float damage) {
        return health -= damage;
    }

    /**
     * The player's health status reflect on its repair amount needed to continue the game
     * @param repairAmount the amount of repairs needed on the player's health
     * @return the amount of repairs in the player's health
     */
    @Override
    public float addHealth(float repairAmount) {
        return health += repairAmount;
    }

    /**
     * Gets the x position of the player
     * @return the position is fixed in the game
     */
    @Override
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
    @Override
    public float getY() {
        return y;
    }

    /**
     * Get the width of the player's position
     * @return representing the width of the player
     */
    @Override
    public float getWidth() {
        return width;
    }

    /**
     * Sets the y position of the player
     * @param y the position is fixed in the game
     */
    public void setY(float y) {
        this.y = y;
    }
}
