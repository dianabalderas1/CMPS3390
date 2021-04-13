package edu.csub.startracker;

import android.graphics.Canvas;

/**
 * GameObject interface for Star Tracker
 * @author Diana Balderas
 * @version 1.0
 */
public interface GameObject {
    void update();
    void draw(Canvas canvas);

    float getX();
    float getY();
    float getWidth();
    float getHeight();

    boolean isAlive();
    float getHealth();
    float takeDamage(float damage);
    float addHealth(float repairAmount);

}

