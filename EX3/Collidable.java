//Arbel Tepper 209222272
package EX3;

import EX2.Ball;
import EX2.Velocity;
import EX2.Point;

/**
 * The interface Collidable.
 */
public interface Collidable {
    /**
     * Gets collision rectangle.
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Hit changes the velocity of the hitting ball according to where the
     * hit happened on the collidable object. It notifies the collidable
     * object it was hit and sets the collidable object's hit velocity.
     *
     * @param hitter
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */

    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Fills collidable with color.
     *
     * @param d the DrawSurface on which to draw.
     */
    //void fillCollidable(DrawSurface d);

    /**
     * Adds the collidable object to the game.
     *
     * @param g the game object
     */
    void addToGame(Game g);
}