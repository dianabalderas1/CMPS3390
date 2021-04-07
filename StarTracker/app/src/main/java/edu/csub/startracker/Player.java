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
public class Player {
    private float x, y, prevX, prevY;
    private final Bitmap playerImg;
    private final Bitmap playerLeft;
    private final Bitmap playerRight;
    private Bitmap curlImage;
    private Paint paint = new Paint();
    private final float dpi;
    private int frameTicks = 0, shotTicks = 0;
    private final Resources res;


    ArrayList<Laser> lasers = new ArrayList<>();


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

        DisplayMetrics dm = res.getDisplayMetrics();
        dpi = dm.densityDpi;

        x = (dm.widthPixels / 2f) - (playerImg.getWidth() / 2f);
        y = (dm.heightPixels * 0.75f);
    }

    /**
     * The position of the player is based on x and y
     * @param touchX representing the position of the player
     * @param touchY representing the position of the player
     */
    public void update(int touchX, int touchY) {
        if(touchX > 0 && touchY > 0) {
            this.x = touchX - (playerImg.getWidth() / 2f);
            this.y = touchY - (playerImg.getHeight() * 2f);
        }

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
            if(!laser.isOnScreen()) {
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
        canvas.drawBitmap(curlImage, this.x, this.y, this.paint);

        // draw all lasers
        for(Laser laser: lasers) {
            laser.draw(canvas);
        }

    }
}
