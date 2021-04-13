package edu.csub.startracker;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.speech.RecognitionService;

/**
 * Enemy01 class for Star Tracker
 * @author Diana Balderas
 * @version 1.0
 */
public class Enemy01 implements GameObject {
    private final float dpi;
    private float x, y, ySpeed;
    private final float width, height;
    private float health = 100f;
    private final Bitmap enemy, enemy_left, enemy_right;
    private Bitmap curImage;
    private int screenWidth, screenHeight;
    private Paint paint = new Paint();

    /**
     * Resources are implemented in the player's layout
     * @param res representing the player's layout and design
     */
    public Enemy01(Resources res, float x, float y) {
        this.x = x;
        this.y = y;

        enemy = BitmapFactory.decodeResource(res, R.mipmap.enemy01);
        enemy_left = BitmapFactory.decodeResource(res, R.mipmap.enemy01_left);
        enemy_right = BitmapFactory.decodeResource(res, R.mipmap.enemy01_right);
        curImage = enemy;
        width = curImage.getWidth();
        height = curImage.getHeight();

        dpi = res.getDisplayMetrics().densityDpi;
        screenHeight = res.getDisplayMetrics().heightPixels;
        screenWidth = res.getDisplayMetrics().widthPixels;
        ySpeed = 0.02f * dpi;


    }

    /**
     * Updates the enemy's health while interacting with player
     */
    @Override
    public void update() {
        float xOff = (float) (0.01f * screenWidth * Math.sin(y / (0.04f * screenHeight)));
        x += xOff;
        curImage = xOff > 0 ? enemy_left: enemy_right;
        if(Math.abs(xOff) < 2) curImage = enemy;

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
