package EX6;

import EX2.Velocity;
import EX3.Block;
import EX3.Sprite;

import java.util.List;
/**
 * The LevelInformation interface represents the information of a game level.
 * It provides methods to retrieve various properties of the level.
 */
public interface LevelInformation {
    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls
     */
    int numberOfBalls();
    /**
     * Returns the number of blocks in the level.
     *
     * @return the number of blocks
     */
    int numberOfBlocks();
    /**
     * Returns the initial velocities of the balls.
     * The size of the returned list should be equal to the number of balls.
     *
     * @return a list of initial ball velocities
     */
    List<Velocity> initialBallVelocities();
    /**
     * Returns the speed of the paddle.
     *
     * @return the paddle speed
     */
    int paddleSpeed();
    /**
     * Returns the width of the paddle.
     *
     * @return the paddle width
     */
    int paddleWidth();
    // the level name will be displayed at the top of the screen.
    /**
     * Returns the name of the level.
     * The name will be displayed at the top of the screen.
     *
     * @return the level name
     */
    String levelName();
    /**
     * Returns the background sprite of the level.
     *
     * @return the background sprite
     */
    List<Sprite> getBackground();
    /**
     * Returns the blocks that make up the level.
     * Each block contains its size, color, and location.
     *
     * @return a list of blocks
     */
    List<Block> blocks();
    /**
     * Returns the number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be less than or equal to the total number of blocks.
     *
     * @return the number of blocks to remove
     */
    int numberOfBlocksToRemove();
}