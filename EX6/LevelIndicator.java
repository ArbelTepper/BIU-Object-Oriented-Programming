// Arbel Tepper 209222272
package EX6;

import EX3.GameLevel;
import EX3.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;


/**
 * The type Score indicator.
 */
public class LevelIndicator implements Sprite {
    private String levelName;

    /**
     * Instantiates a new Level indicator.
     *
     * @param levelName the level name
     */
    public LevelIndicator(String levelName) {
        this.levelName = levelName;
    }

    /**
     * Gets level name.
     *
     * @return the level name
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * Draws the sprite object on the given DrawSurface object.
     *
     * @param d the DrawSurface object to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(500, 15, "Level Name: " + getLevelName(), 15);
    }

    /**
     * Notifies the sprite object that a unit of time has passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * Adds the sprite object to the given Game object.
     *
     * @param g the Game object to add the sprite to.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
