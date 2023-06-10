//Arbel Tepper 209222272
package EX3;

import EX2.Ball;
import EX2.Line;
import EX2.Point;
import EX2.Velocity;
import EX5.HitListener;
import EX5.HitNotifier;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Block.
 */
public class Block implements Collidable, Sprite, HitNotifier  {
    /**
     * The constant EPSILON represents the small change required to create
     * pointAsLine in the method "hit".
     */
    static final double EPSILON = 0.00001;
    private Rectangle shape;
    private Color color;
    private boolean hit;
    private Velocity hitVelocity;
    private List<HitListener> hitListeners;

    /**
     * Creates a new block with the specified shape.
     *
     * @param shape the shape of the block represented as a Rectangle object
     */
    public Block(Rectangle shape) {
        this.shape = shape;
        this.hit = false;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Sets the color of this block.
     *
     * @param color the new color for this block
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Returns the color of this block.
     *
     * @return the color of this block
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Sets the hit velocity of this block.
     *
     * @param v the velocity of the hitting ball on impact.
     */
    public void setHitVelocity(Velocity v) {
        this.hitVelocity = v;
    }

    /**
     * Returns the hit velocity of this block.
     *
     * @return the hit velocity of this block
     */
    public Velocity getHitVelocity() {
        return this.hitVelocity;
    }
    /**
     * Returns the collision rectangle of this block.
     *
     * @return the collision rectangle of this block
     */
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }
    /**
     * Returns the new velocity of the object after it collides with a given point.
     * It does that by creating a line object from the point.
     * The start and end points of the line are generated using a very small
     * variations to the X and Y values of the collision point.
     * Then, it checks for a collision of the new line with each border of
     * the block.
     *
     * <p>If a collision is detected with either the upper or the bottom
     * borders, the Y value of the new velocity generated will be negative to
     * the current velocity's Y value.
     *
     * <p>If a collision is detected with either the right or the left
     * borders, the X value of the new velocity generated will be negative to
     * the current velocity's X value.
     *
     * @param hitter the hitting ball
     * @param collisionPoint the point at which the collision occurs
     * @param currentVelocity the current velocity of the object
     * @return the new velocity of the object after the collision, or null if there is no collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        Line pointAsLine = new Line(collisionPoint.getX() - EPSILON,
                collisionPoint.getY() - EPSILON,
                collisionPoint.getX() + EPSILON,
                collisionPoint.getY() + EPSILON);


        this.setHitVelocity(currentVelocity);
        if (this.shape.getUp().isIntersecting(pointAsLine)
                || this.shape.getDown().isIntersecting(pointAsLine)) {
            this.hit = true;
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx(),
                    -1 * currentVelocity.getDy());
        }
        if (this.shape.getLeft().isIntersecting(pointAsLine)
                || this.shape.getRight().isIntersecting(pointAsLine)) {
            this.hit = true;
            this.notifyHit(hitter);
            return new Velocity(-1 * currentVelocity.getDx(),
                    currentVelocity.getDy());
        }
        return null;
    }
    /**
     * Draws the object on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the block
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.getColor());
        this.shape.fillRectangle(d);
        this.shape.drawRectangle(d);
    }

    /**
     * Does nothing at the moment and does not affect anything.
     */
    @Override
    public void timePassed() {

    }

    /**
     * Adds the block to the given game by registering it as both a sprite
     * and a collidable.
     *
     * @param g the game to which to add the block
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Adds the block to the given game by registering it as a collidable only.
     * This means that it is not a regular block which is also a Sprite and
     * hence is not drawn. It still functions as a Collidable.
     *
     * @param g the game to which to add the block
     */
    public void addToGameAsDeathZone(GameLevel g) {
        g.addCollidable(this);
    }

    /**
     * getShape.
     *
     * @return the rectangle shape of this block
     */
    public Rectangle getShape() {
         return this.shape;
     }

    /**
     * Remove from game.
     * Removes the block, which is both a Sprite and a Collidable object, from
     * the game.
     *
     * @param gameLevel the game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * Notify hit.
     * When a hit occurred, it notifies all listeners about a hit event
     *
     * @param hitter the hitter
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Add hit listener.
     * @param hl the hit listener to be added.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hit listener.
     * @param hl the hit listener to be removed.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Gets hit listeners.
     *
     * @return the hit listeners
     */
    public List<HitListener> getHitListeners() {
        return this.hitListeners;
    }
}
