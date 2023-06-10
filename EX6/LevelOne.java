package EX6;

import EX2.Point;
import EX2.Velocity;
import EX3.Block;
import EX3.Rectangle;
import EX3.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static EX3.GameLevel.BALL_SPEED;
import static EX3.GameLevel.GUI_HEIGHT;
import static EX3.GameLevel.GUI_WIDTH;
import static EX3.GameLevel.BLOCK_WIDTH;
import static EX3.GameLevel.BLOCK_HEIGHT;


/**
 * The LevelOne class represents the information for level one in the game.
 * It implements the LevelInformation interface.
 */
public class LevelOne implements LevelInformation {
    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }

    /**
     * Returns the number of blocks in the level.
     *
     * @return the number of blocks
     */
    @Override
    public int numberOfBlocks() {
        return 1;
    }

    /**
     * Returns the initial velocities of the balls.
     * The size of the returned list should be equal to the number of balls.
     *
     * @return a list of initial ball velocities
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        Velocity firstBall = Velocity.fromAngleAndSpeed(180, BALL_SPEED);
        velocities.add(firstBall);
        return velocities;
    }

    /**
     * Returns the speed of the paddle.
     *
     * @return the paddle speed
     */
    @Override
    public int paddleSpeed() {
        return 5;
    }

    /**
     * Returns the width of the paddle.
     *
     * @return the paddle width
     */
    @Override
    public int paddleWidth() {
        return 100;
    }

    /**
     * Returns the name of the level.
     * The name will be displayed at the top of the screen.
     *
     * @return the level name
     */
    @Override
    public String levelName() {
        return "Level One";
    }

    /**
     * Returns the list of sprites that together comprise the level background.
     *
     * @return the background sprite list
     */
    @Override
    public List<Sprite> getBackground() {
        List<Sprite> wholeBackground = new ArrayList<>();
        Block part1 = new Block(new Rectangle(new Point(0, 0),
                GUI_WIDTH, GUI_HEIGHT));
        //Choosing a background color using the HSB scale.
        part1.setColor(Color.getHSBColor(0.555f, 0.6f, 0.6f));
        wholeBackground.add(part1);
        return wholeBackground;
    }

    /**
     * Returns the blocks that make up the level.
     * Each block contains its size, color, and location.
     *
     * @return a list of blocks
     */
    @Override
    public List<Block> blocks() {
        List<Block> wholeBlocks = new ArrayList<>();
        Block one = new Block(new Rectangle(new Point(
                GUI_WIDTH / 2 - BLOCK_WIDTH / 2, 120),
                BLOCK_WIDTH, BLOCK_HEIGHT));
        one.setColor(Color.red);
        wholeBlocks.add(one);
        return wholeBlocks;
    }

    /**
     * Returns the number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be less than or equal to the total number of blocks.
     *
     * @return the number of blocks to remove
     */
    @Override
    public int numberOfBlocksToRemove() {
        return numberOfBlocks();
    }
}
