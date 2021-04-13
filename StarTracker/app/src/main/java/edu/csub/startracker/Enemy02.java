package edu.csub.startracker;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.speech.RecognitionService;

import java.util.Random;

/**
 * Enemy02 class for Star Tracker
 * @author Diana Balderas
 * @version 1.0
 */
public class Enemy02 implements GameObject {
    private float x, y, ySpeed;
    private float health = 100;
    private final Bitmap enemy;
    private final Bitmap enemy_fast;
    private Bitmap curImage;
    private final int screenWidth, screenHeight, dpi;
    private final int width, height;
    private Paint paint = new Paint();
    private int frameTick = 0, launchTick;

    /**
     * Resources are implemented in the player's layout
     * @param res representing the player's layout and design
     */
    public Enemy02(Resources res, float x, float y) {
        dpi = res.getDisplayMetrics().densityDpi;
        screenWidth = res.getDisplayMetrics().widthPixels;
        screenHeight = res.getDisplayMetrics().heightPixels;

        enemy = BitmapFactory.decodeResource(res, R.mipmap.enemy02);
        enemy_fast = BitmapFactory.decodeResource(res, R.mipmap.enemy02_fast);
        curImage = enemy;

        width = curImage.getWidth();
        height = curImage.getHeight();

        this.x = x;
        this.y = y;

        ySpeed = 0.01f * dpi;

        launchTick = new Random().nextInt(120-30) + 30;
    }

    /**
     * Updates the enemy's health while interacting with player
     */
    @Override
    public void update() {
        // Start slow wait some time
        frameTick++;

        if(frameTick == launchTick) {
            // switch images and go fast
            curImage = enemy_fast;
            ySpeed *= 4f;

        }

        // move on the Y
        y += ySpeed;
    }

    /**
     * The canvas of the game includes the images and designs
     * @param canvas representing the draw of the game
     */
    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(curImage, x, y, paint);

    }

    /**
     * Gets the x position of the enemy
     * @return the position is fixed in the game
     */
    @Override
    public float getX() {
        return x;
    }

    /**
     * Gets the y position of the enemy
     * @return the position is fixed in the game
     */
    @Override
    public float getY() {
        return y;
    }

    /**
     * Get the width of the enemy's position
     * @return representing the width of the enemy
     */
    @Override
    public float getWidth() {
        return width;
    }

    /**
     * Get the height of the enemy's position
     * @return representing the height of the enemy
     */
    @Override
    public float getHeight() {
        return height;
    }

    /**
     * Determines of the enemy is alive based on their health
     * @return the health of the enemy
     */
    @Override
    public boolean isAlive() {
        return health > 0f;
    }

    /**
     * The enemy receives a health status based on playing the game
     * @return the health of the enemy
     */
    @Override
    public float getHealth() {
        return health;
    }

    /**
     * The enemy's health notifies if there were any damage
     * @param damage the injuries on the enemy's attack
     * @return the health damage the enemy received
     */
    @Override
    public float takeDamage(float damage) {
        return health -= damage;
    }

    /**
     * The enemy's health status reflect on its repair amount needed to continue the game
     * @param repairAmount the amount of repairs needed on the enemy's health
     * @return the amount of repairs in the enemy's health
     */
    @Override
    public float addHealth(float repairAmount) {
        return health += repairAmount;
    }
}
