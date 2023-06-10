//Arbel Tepper 209222272
package EX3;

import EX2.Ball;
import EX2.Line;
import EX2.Point;
import EX2.Velocity;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Paddle class represents the player's paddle in the game. It implements
 * the Sprite and Collidable interfaces and is responsible for handling the
 * paddle's movement and collision detection with the ball.
 */
public class Paddle implements Sprite, Collidable {
    static final int PUDDLE_HEIGHT = 10;
    static final int GUI_WIDTH = 800;
    static final int FRAME_BLOCK_WIDTH = 20;
    static final int NUMBER_OF_REGIONS = 5;


    /**
     * The constant EPSILON represents the small change required to create
     * pointAsLine in the method "hit".
     */
    static final double EPSILON = 0.00001;
    private biuoop.KeyboardSensor keyboard;
    private Block block;
    private Color color;
    private int movementDistance;
    private List<Line> regions;
    private int numberOfRegions;
    private double regionDistance;

    /**
     * Creates a new Paddle object with the given keyboard sensor, paddle
     * speed and paddle width .
     *
     * @param keyboard the keyboard sensor to use for movement input
     * @param paddleSpeed the speed of the puddle
     * @param paddleWidth the width of the puddle
     */
    public Paddle(biuoop.KeyboardSensor keyboard, int paddleSpeed,
                  int paddleWidth) {
        this.keyboard = keyboard;
        this.color = Color.orange;
        this.block  =
                new Block(new Rectangle(new Point(
                        GUI_WIDTH / 2 - paddleWidth / 2,
                        580 - PUDDLE_HEIGHT),
                        paddleWidth, PUDDLE_HEIGHT));
        this.block.setColor(this.color);
        this.movementDistance = paddleSpeed;
        this.numberOfRegions = NUMBER_OF_REGIONS;
        // regionDistance is the distance of each region.
        // it is calculated by dividing the distance of the upper line of the
        // paddle by how many regions there should be.
        this.regionDistance = this.getUp().getlength() / this.numberOfRegions;
        this.regions = new ArrayList<>();
        // Each line is constructed using 2 points. All the lines have
        // the same Y value - that of the upper line of the paddle.
        // However, each line has different X values.
        // The X start value of each line is the X value of the upper
        // left point plus i times the region distance.
        // The X end value of each line is the X value of the upper
        // left point plus i+1 times the region distance.
        // Where i is the number of the region (counting from 0, e.g. the
        // first region is number 0).
        for (int i = 0; i < this.numberOfRegions; i++) {
            Line regionI = new Line(this.getUpperLeft().getX() + i * this.regionDistance,
                            this.getUpperLeft().getY(),
                                    this.getUpperLeft().getX() + (i + 1) * this.regionDistance,
                            this.getUpperLeft().getY());
            this.regions.add(regionI);
        }
    }
    /**
     * Updates the regions of the paddle.
     * Divides the paddle into 'numberOfRegions' segments and creates a line for each segment.
     * The lines are then saved to the 'regions' list.
     * The distance between each segment is calculated by dividing the paddle's width by 'numberOfRegions'.
     * Then, the list of regions is updated with each line.
     */

    public void updateRegions() {
        for (int i = 0; i < this.numberOfRegions; i++) {
            Line regionI = new Line(this.getUpperLeft().getX() + i * this.regionDistance,
                    this.getUpperLeft().getY(),
                    this.getUpperLeft().getX() + (i + 1) * this.regionDistance,
                    this.getUpperLeft().getY());
            this.regions.set(i, regionI);
        }
    }
    /**
     * Returns the upper left point of the paddle's collision rectangle.
     *
     * @return the upper left point of the collision rectangle
     */
    public Point getUpperLeft() {
        return this.block.getCollisionRectangle().getUpperLeft();
    }
    /**
     * Returns the upper right point of the paddle's collision rectangle.
     *
     * @return the upper right point of the collision rectangle
     */
    public Point getUpperRight() {
        return this.block.getCollisionRectangle().getUpperRight();
    }
    /**
     * Returns the paddle's collision rectangle.
     *
     * @return the collision rectangle of the paddle
     */
    public Rectangle getShape() {
        return this.block.getShape();
    }
    /**
     * getUp.
     *
     * @return the upper line of the paddle's collision rectangle
     */
    public Line getUp() {
        return this.block.getCollisionRectangle().getUp();
    }
    /**
     * Moves the paddle to the left by the specified distance. If the movement to the left
     * exceeds the frame block to the left, the paddle will respawn at the leftmost point
     * of the frame block. Otherwise, the paddle will move to the
     * left by the specified distance.
     */
    public void moveLeft() {
        if (this.getUpperLeft().getX() - this.movementDistance < FRAME_BLOCK_WIDTH) {
            this.block = new Block(new Rectangle(
                    // The X value of the upper point of the new block will
                    // be the frame width.
                    new Point(FRAME_BLOCK_WIDTH, this.getUpperLeft().getY()),
                    this.block.getCollisionRectangle().getWidth(),
                    this.block.getCollisionRectangle().getHeight()));
        } else {
            this.block = new Block(new Rectangle(
                    new Point(this.getUpperLeft().getX()
                            - this.movementDistance, this.getUpperLeft().getY()),
                    this.block.getCollisionRectangle().getWidth(),
                    this.block.getCollisionRectangle().getHeight()));
        }
        this.block.setColor(this.color);
        updateRegions();
    }
    /**
     * Moves the paddle to the right by the specified distance. If the movement
     * to the right exceeds the frame block to the right, the paddle will
     * respawn at the rightmost point of the frame. Otherwise, the
     * paddle will move to the right by the specified distance.
     */
    public void moveRight() {
        // if the movement to the right exceeds the frame block to the right
        if (this.getUpperRight().getX() + this.movementDistance > GUI_WIDTH - FRAME_BLOCK_WIDTH) {
            this.block = new Block(new Rectangle(
                    // The X value of upper point of the new block will be:
                    // Take the GUI width, subtract from it the width of the
                    // frame, and subtract from that the width of the block.
                    // If the paddle is already at the end of the screen, it
                    // *should* respawn in the same place.
                    new Point(GUI_WIDTH - FRAME_BLOCK_WIDTH - this.block.getCollisionRectangle().getWidth(),
                            this.getUpperLeft().getY()),
                    this.block.getCollisionRectangle().getWidth(),
                    this.block.getCollisionRectangle().getHeight()));
        } else {
            this.block = new Block(new Rectangle(
                    new Point(this.getUpperLeft().getX()
                            + this.movementDistance,
                            this.getUpperLeft().getY()),
                    this.block.getCollisionRectangle().getWidth(),
                    this.block.getCollisionRectangle().getHeight()));
        }
        this.block.setColor(this.color);
        updateRegions();
    }

    /**
     * This method is called from the game loop on every iteration, and
     * checks whether the left or right keys are pressed. If the left key is
     * pressed, the paddle will move to the left. If the right key is
     * pressed, the paddle will move to the right.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
    /**
     * Draws the paddle block on the given DrawSurface.
     *
     * @param d the DrawSurface to draw the paddle on.
     */
    public void drawOn(DrawSurface d) {
        this.block.drawOn(d);
    }

    /**
     * Gets collision rectangle.
     *
     * @return the "collision shape" of the object.
     */
    public Rectangle getCollisionRectangle() {
        return this.block.getCollisionRectangle();
    }
    /**
     * Hit changes the velocity of the hitting ball according to where the
     * hit happened on the collidable object.
     *
     * @param hitter
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */

    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        Line pointAsLine = new Line(collisionPoint.getX() - EPSILON,
                collisionPoint.getY() - EPSILON,
                collisionPoint.getX() + EPSILON,
                collisionPoint.getY() + EPSILON);

        for (int i = 0; i < this.numberOfRegions; i++) {
            if (this.regions.get(i).isIntersecting(pointAsLine)) {
                if (i == 2) {
                    return new Velocity(currentVelocity.getDx(),
                            -1 * currentVelocity.getDy());
                }
                // 300 is the desired angle for region1 and 30 is the
                // difference of each angle from its previous.
                double newDx =
                        Velocity.fromAngleAndSpeed(-60 + 30 * i,
                                GameLevel.BALL_SPEED).getDx();
                return new Velocity(newDx, -1 * currentVelocity.getDy());
            }
        }
        if (this.getShape().getDown().isIntersecting(pointAsLine)) {
            return new Velocity(currentVelocity.getDx(),
                    -1 * currentVelocity.getDy());
        }
        if (this.getShape().getLeft().isIntersecting(pointAsLine)
                || this.getShape().getRight().isIntersecting(pointAsLine)) {
            return new Velocity(-1 * currentVelocity.getDx(),
                    currentVelocity.getDy());
        }
        return null;
    }

    /**
     * Adds the paddle to the given game by registering it as both a sprite
     * and a collidable.
     *
     * @param g the game to which to add the block
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
