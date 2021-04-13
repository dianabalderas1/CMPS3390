package edu.csub.startracker;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Player class for Star Tracker
 * @author Diana Balderas
 * @version 1.0
 */
public class Player implements GameObject {
    private float x, y, prevX, prevY;
    private final Bitmap playerImg;
    private final Bitmap playerLeft;
    private final Bitmap playerRight;
    private Bitmap curlImage;
    private Paint paint = new Paint();
    private final float dpi;
    private int frameTicks = 0, shotTicks = 0;
    private final Resources res;
    private final int width, height;


    ArrayList<Laser> lasers = new ArrayList<>();
    private float health = 100f;


    /**
     * Resources are implemented in the player's layout
     * @param res representing the player's layout and design
     */
    public Player(Resources res) {
        this.res = res;
        playerImg = BitmapFactory.decodeResource(res, R.mipmap.player);
        playerLeft = BitmapFactory.decodeResource(res, R.mipmap.player_left);
        playerRight = BitmapFactory.decodeResource(res, R.mipmap.player_right);

        curlImage = playerImg;
        width = curlImage.getWidth();
        height = curlImage.getHeight();

        DisplayMetrics dm = res.getDisplayMetrics();
        dpi = dm.densityDpi;

        x = (dm.widthPixels / 2f) - (playerImg.getWidth() / 2f);
        y = (dm.heightPixels * 0.75f);
    }

    /**
     * Player touches the objects in the game
     * @param touchX width of player's touch movement
     * @param touchY height of the player to movement
     */
    public void updateTouch(int touchX, int touchY) {
        if(touchX > 0 && touchY > 0) {
            this.x = touchX - (playerImg.getWidth() / 2f);
            this.y = touchY - (playerImg.getHeight() * 2f);
        }

    }

    /**
     * Updates the player's health while interacting with enemy
     */
    @Override
    public void update() {
        if(health <= 0) return;

        if(Math.abs(x - prevX) < 0.04 * dpi) {
            frameTicks++;
        } else {
            frameTicks = 0;
        }

        if(this.x < prevX - 0.04 * dpi) {
            curlImage = playerLeft;
        } else if(this.x > prevX + 0.04 * dpi) {
            curlImage = playerRight;
        } else if(frameTicks > 5){
            curlImage = playerImg;
        }

        prevX = x;
        prevY = y;

        // Increase shotTicks
        shotTicks++;

        // see if we need to shoot
        if(shotTicks >= 10) {
            // shoot here
            Laser tmp = new Laser(this.res);
            tmp.setX(x + (playerImg.getWidth() / 2f) - tmp.getMidX());
            tmp.setY(y - tmp.getHeight() / 2f);
            lasers.add(tmp);
            // reset the shotTicks
            shotTicks = 0;
        }

        // remove lasers that are off screen
        for(Iterator<Laser> iterator = lasers.iterator(); iterator.hasNext();) {
            Laser laser = iterator.next();
            if(!laser.isOnScreen() || !laser.isAlive()) {
                iterator.remove();
            }
        }

        // update all lasers
        for(Laser laser: lasers) {
            laser.update();
        }

    }


    /**
     * The canvas of the game includes the images and designs
     * @param canvas representing the draw of the game
     */
    public void draw(Canvas canvas) {
        if(health <= 0) return;
        canvas.drawBitmap(curlImage, this.x, this.y, this.paint);

        // draw all lasers
        for(Laser laser: lasers) {
            laser.draw(canvas);
        }

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
     * Get the height of the player's position
     * @return representing the position of the player
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
     * Get lasers for the player to attack on the enemies
     * @return the lasers needed for the player
     */
    public ArrayList<Laser> getLasers() {
        return lasers;
    }
}
