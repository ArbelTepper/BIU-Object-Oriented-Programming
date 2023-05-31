//Arbel Tepper 209222272
package EX3;

import EX2.Point;

/**
 * The CollisionInfo class represents information about a collision between a
 * ball and a collidable object.
 */
public class CollisionInfo {
    // the point at which the collision occurs.
    private Point collisionPoint;
    private Collidable collisionObject;



    /**
     * Creates a new CollisionInfo object with the given collision point and
     * collidable object.
     *
     * @param collisionPoint the point at which the collision occurs
     * @param collisionObject the collidable object involved in the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }
    /**
     * Returns the point at which the collision occurs.
     *
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}

