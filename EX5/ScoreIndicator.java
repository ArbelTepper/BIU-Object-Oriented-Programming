// Arbel Tepper 209222272
package EX5;

import EX3.Game;
import EX3.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;


/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Instantiates a new Score indicator.
     *
     * @param score the score counter from Game
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * Draws the sprite object on the given DrawSurface object.
     *
     * @param d the DrawSurface object to draw on.
     */


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(363, 15, "Score: " + this.score.getValue(), 15);
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
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
