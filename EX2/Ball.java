//Arbel Tepper 209222272
package EX2;

import EX3.CollisionInfo;
import EX3.GameLevel;
import EX3.GameEnvironment;
import EX3.Sprite;
import biuoop.DrawSurface;

/**
 * This class represents a ball object in a game. It has methods for moving
 * the ball forward by one step and adding the ball as a Sprite to a game.
 */
public class Ball implements Sprite {
    /**
     * The constant COMPARISON_THRESHOLD holds the accuracy value for
     * comparing doubles.
     */
    static final double COMPARISON_THRESHOLD = 0.00001;
    /**
     * The Epsilon.
     */
    static final double EPSILON = 0.01;

    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Constructs a ball object with the given center point, radius, and color.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
// constructor
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = InvalidInput.positiveRadius(r);
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * Constructs a ball object with the given X and Y values for its center
     * point, radius, and color.
     *
     * @param x     the x value.
     * @param y     the y value.
     * @param r     the value of the radius of the ball.
     * @param color the color of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = InvalidInput.positiveRadius(r);
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * Gets x.
     *
     * @return the x value of the ball.
     */
// accessors
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y.
     *
     * @return the y value of the ball.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets size.
     *
     * @return the size of the ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Gets color.
     *
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw on.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Sets velocity.
     *
     * @param v the v
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Sets center.
     *
     * @param newCenter the new center
     */
    public void setCenter(Point newCenter) {
        this.center = newCenter;
    }

    /**
     * Sets game environment.
     *
     * @param gameEnvironment the game environment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Moves the ball one step according to its velocity, and handles
     * collisions with objects in the game environment.
     */
    public void moveOneStep() {
        // Calculates the end point of the ball's movement based on its
        // current velocity.
        Point endStep =
                new Point(this.center.getX() + this.getVelocity().getDx(),
                        this.center.getY() + this.getVelocity().getDy());
        Line trajectory = new Line(this.center, endStep);
        // Gets the closest object the ball collides with along its
        // trajectory, if any.
        CollisionInfo info =
                this.gameEnvironment.getClosestCollision(trajectory);
        if (info == null) {
            this.setCenter(endStep);
        } else {
            // move the ball to "almost" the hit point, but just slightly
            // before it.
            Velocity velocity = new Velocity(-1 * EPSILON, -1 * EPSILON);
            this.center = velocity.applyToPoint(this.center);

            // Sets the ball's velocity to the velocity returned by the hit
            // method of the object it collided with - either a Block or the
            // Paddle.
            this.setVelocity(info.collisionObject().hit(this,
                    info.collisionPoint(), this.getVelocity()));
        }
    }

    /**
     * Move one step within frame receives the x and y coordinates of the
     * starting point of a frame and the difference between them and the end
     * points of the frame. It will move the ball forward up to the
     * border of the frame, at which point it will change direction.
     *
     * @param startHeight the Y value of the start point of the frame.
     * @param startWidth  the X value of the start point of the frame.
     * @param endHeight   the height difference to the end point of the frame.
     * @param endWidth    the width difference to the end point of the frame.
     */
    public void moveOneStepWithinFrame(double startHeight, double startWidth,
                                       double endHeight, double endWidth) {
        double y = this.center.getY();
        double x = this.center.getX();
        double r = this.radius;
        // if the y value is not within the frame range
        if (y + r + this.getVelocity().getDy() >= endHeight + COMPARISON_THRESHOLD
                || y - r + this.getVelocity().getDy() <= startHeight + COMPARISON_THRESHOLD) {
            // temporary placeholder for the ball's velocity for comfort.
            Velocity v = this.getVelocity();
            this.setVelocity(v.getDx(), -1 * velocity.getDy());
        }
        // if the x value is not within the frame range
        if (x + r + this.getVelocity().getDx() >= endWidth + COMPARISON_THRESHOLD
                || x - r + this.getVelocity().getDx() <= startWidth + COMPARISON_THRESHOLD) {
            // temporary placeholder for the ball's velocity for comfort.
            Velocity v = this.getVelocity();
            this.setVelocity(-1 * v.getDx(), velocity.getDy());
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }


    /**
     * This method is called every time a unit of time passed in the game, and
     * it moves the ball by one step.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * This method adds the ball as a sprite to the specified game.
     *
     * @param g the game to which the ball will be added as a sprite
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove from game.
     * Removes the ball, which is solely a Sprite object, from the game.
     *
     * @param gameLevel the game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}