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
 * The LevelThree class represents the information for level three in the game.
 * It implements the LevelInformation interface.
 */
public class LevelThree implements LevelInformation {
    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls
     */
    @Override
    public int numberOfBalls() {
        return 2;
    }

    /**
     * Returns the number of blocks in the level.
     *
     * @return the number of blocks
     */
    @Override
    public int numberOfBlocks() {
        return 40;
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
        return 10;
    }

    /**
     * Returns the width of the paddle.
     *
     * @return the paddle width
     */
    @Override
    public int paddleWidth() {
        return 80;
    }

    /**
     * Returns the name of the level.
     * The name will be displayed at the top of the screen.
     *
     * @return the level name
     */
    @Override
    public String levelName() {
        return "Level Three";
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
        part1.setColor(Color.getHSBColor(0.8f, 0.4f, 0.79f));
        wholeBackground.add(part1);
        return wholeBackground;
    }
    /**
     * Creates a row of blocks with the specified number of blocks, starting
     * from the given left start point of the row.
     * Each block is added to the provided list of blocks with the specified color.
     *
     * @param numOfBlocks    the number of blocks in the row
     * @param leftStartOfRow the left start point of the row
     * @param wholeBlocks    the list of blocks to add the created blocks to
     * @param color          the color of the blocks in the row
     */
    public void createRow(int numOfBlocks, Point leftStartOfRow,
                          List<Block> wholeBlocks, Color color) {
        for (int i = 0; i < numOfBlocks; i++) {
            //x difference offsets the distance of each block from its previous.
            //Technically, from the start point.
            int xDifference = i * BLOCK_WIDTH;
            Point startOfBlock =
                    new Point(leftStartOfRow.getX() + xDifference,
                            leftStartOfRow.getY());
            Block current =
                    new Block(new Rectangle(startOfBlock, BLOCK_WIDTH,
                            BLOCK_HEIGHT));
            current.setColor(color);
            wholeBlocks.add(current);
        }
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
        List<Integer> numOfBlocksInRowI = List.of(10, 9, 8, 7, 6);
        List<Color> colors = List.of(Color.gray, Color.red, Color.yellow,
                Color.blue, Color.white);
        // numOfBlocksInRowI.size() = number of rows
        for (int i = 0; i < numOfBlocksInRowI.size(); i++) {
            int xDifference = i * BLOCK_WIDTH;
            int yDifference = i * BLOCK_HEIGHT;
            Point leftStartOfRow = new Point(280 + xDifference,
                    120 + yDifference);
            createRow(numOfBlocksInRowI.get(i), leftStartOfRow,
                    wholeBlocks, colors.get(i));
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
        return numberOfBlocks();
    }
}
