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
import static EX3.GameLevel.BLOCK_HEIGHT;

/**
 * The LevelTwo class represents the information for level two in the game.
 * It implements the LevelInformation interface.
 */
public class LevelTwo implements LevelInformation {

    public static final double BLOCK_WIDTH = 50.66667;
    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls
     */
    @Override
    public int numberOfBalls() {
        return 10;
    }

    /**
     * Returns the number of blocks in the level.
     *
     * @return the number of blocks
     */
    @Override
    public int numberOfBlocks() {
        return 15;
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
        double angle = (double) 160 / (numberOfBalls() - 1);
        for (int i = 0; i < numberOfBalls(); i++) {
            velocities.add(Velocity.fromAngleAndSpeed(100
                    + angle * i, BALL_SPEED));
        }
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
        return 400;
    }

    /**
     * Returns the name of the level.
     * The name will be displayed at the top of the screen.
     *
     * @return the level name
     */
    @Override
    public String levelName() {
        return "Level Two";
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
        part1.setColor(Color.getHSBColor(0.575f, 0.3f, 0.7f));
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

        List<Color> colors = new ArrayList<>();
        colors.add(Color.red);
        colors.add(Color.orange);
        colors.add(Color.yellow);
        colors.add(Color.green);
        colors.add(Color.blue);
        colors.add(Color.pink);
        colors.add(Color.cyan);
        List<Block> wholeBlocks = new ArrayList<>();
        int height = 120;
        for (int i = 0; i < numberOfBlocks(); i++) {
            double xDifference = 20 + i * BLOCK_WIDTH;
            Point leftup = new Point(xDifference, height);
            Block current = new Block(new Rectangle(leftup, BLOCK_WIDTH,
                    BLOCK_HEIGHT));
            if (i <= 1) {
                current.setColor(colors.get(0));
            }
            if (i >= 2 && i <= 3) {
                current.setColor(colors.get(1));
            }
            if (i >= 4 && i <= 5) {
                current.setColor(colors.get(2));
            }
            if (i >= 6 && i <= 8) {
                current.setColor(colors.get(3));
            }
            if (i >= 9 && i <= 10) {
                current.setColor(colors.get(4));
            }
            if (i >= 11 && i <= 12) {
                current.setColor(colors.get(5));
            }
            if (i >= 13) {
                current.setColor(colors.get(6));
            }
            wholeBlocks.add(current);
        }
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
        return 15;
    }
}
