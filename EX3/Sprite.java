//Arbel Tepper 209222272
package EX3;

import biuoop.DrawSurface;

/**
 * The interface Sprite is an interface for game objects that can be drawn on
 * a DrawSurface, can be notified when time has passed, and can be added to a
 * Game object.
 */
public interface Sprite {
    /**
     * Draws the sprite object on the given DrawSurface object.
     *
     * @param d the DrawSurface object to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite object that a unit of time has passed.
     */
    void timePassed();

    /**
     * Adds the sprite object to the given Game object.
     *
     * @param g the Game object to add the sprite to.
     */
    void addToGame(Game g);
}
