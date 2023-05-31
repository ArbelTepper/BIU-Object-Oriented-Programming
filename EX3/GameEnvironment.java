//Arbel Tepper 209222272
package EX3;

import EX2.Line;
import EX2.Point;

import java.util.ArrayList;
import java.util.List;


/**
 * The GameEnvironment class represents the game environment where all the
 * collidable game objects are held.
 */
public class GameEnvironment {
    private List<Collidable> collisions;

    /**
     * Constructs a new GameEnvironment with an empty list of collisions.
     */
    public GameEnvironment() {
        this.collisions = new ArrayList<>();
    }

    /**
     * Gets collisions.
     *
     * @return the list of collidable objects in the game environment
     */
    public List<Collidable> getCollisions() {
        return collisions;
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        this.collisions.add(c);
    }

    /**
     * Given a trajectory (a line), returns information about the closest
     * collision that is going to occur. We assume that an object moves from
     * line.start() to line.end().
     * If no collision occurs, null is returned.
     *
     * @param trajectory the trajectory of the moving object
     * @return information about the closest collision that is going to occur, or null if no collision occurs
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Collidable> collisions =
                new ArrayList<Collidable>(this.collisions);
        for (Collidable object : collisions) {
            Point collisionPoint =
                    trajectory.closestIntersectionToStartOfLine(object.getCollisionRectangle());
            if (collisionPoint != null) {
                return new CollisionInfo(collisionPoint, object);
            }
        }
        return null;
    }
    /**
     * Remove collidable.
     *
     * @param c the collidable to be removed
     */
    public void removeCollidable(Collidable c) {
        this.collisions.remove(c);
    }
}