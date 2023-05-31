//Arbel Tepper 209222272
package EX3;

import EX2.Ball;
import EX2.Point;
import EX2.Velocity;

import EX5.Counter;
import EX5.HitListener;
import EX5.ScoreIndicator;
import EX5.ScoreTrackingListener;
import EX5.BallRemover;
import EX5.BlockRemover;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a game object. It contains a collection of sprites,
 * a game environment, and a graphical user interface. The game involves the
 * creation of blocks and a ball which are added to the game. The ball
 * bounces around and hits the blocks.
 */
public class Game {

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter remainedBlocks;
    private Counter remainedBalls;
    private Counter score;

    /**
     * Constructs a new Game object with an empty collection of sprites, a new
     * game environment, and a new GUI with a specified width and height.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("Game", GUI_WIDTH, GUI_HEIGHT);
        this.remainedBlocks = new Counter(NUM_OF_BLOCKS);
        this.remainedBalls = new Counter(NUM_OF_BALLS);
        this.score = new Counter(0);
    }

    /**
     * Adds a new collidable object to the collisions list in the game
     * environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a new sprite object to the list of sprites.
     *
     * @param s the sprite object to add
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.

    /**
     * The constant ROW_ONE_BLOCK_NUM.
     */
    public static final int ROW_ONE_BLOCK_NUM = 12;
    /**
     * The constant ROW_TWO_BLOCK_NUM.
     */
    public static final int ROW_TWO_BLOCK_NUM = ROW_ONE_BLOCK_NUM - 1;
    /**
     * The constant ROW_THREE_BLOCK_NUM.
     */
    public static final int ROW_THREE_BLOCK_NUM = ROW_TWO_BLOCK_NUM - 1;
    /**
     * The constant ROW_FOUR_BLOCK_NUM.
     */
    public static final int ROW_FOUR_BLOCK_NUM = ROW_THREE_BLOCK_NUM - 1;
    /**
     * The constant ROW_FIVE_BLOCK_NUM.
     */
    public static final int ROW_FIVE_BLOCK_NUM = ROW_FOUR_BLOCK_NUM - 1;
    /**
     * The constant ROW_SIX_BLOCK_NUM.
     */
    public static final int ROW_SIX_BLOCK_NUM = ROW_FIVE_BLOCK_NUM - 1;

    /**
     * The constant NUM_OF_BLOCKS.
     */
    public static final int NUM_OF_BLOCKS =
            ROW_ONE_BLOCK_NUM + ROW_TWO_BLOCK_NUM
            + ROW_THREE_BLOCK_NUM + ROW_FOUR_BLOCK_NUM + ROW_FIVE_BLOCK_NUM
            + ROW_SIX_BLOCK_NUM;

    /**
     * The constant NUM_OF_BALLS.
     */
    public static final int NUM_OF_BALLS = 3;
    /**
     * The constant GUI_WIDTH.
     */
    public static final int GUI_WIDTH = 800;
    /**
     * The constant GUI_HEIGHT.
     */
    public static final int GUI_HEIGHT = 600;
    /**
     * The constant BLOCK_WIDTH.
     */
    public static final int BLOCK_WIDTH = 50;
    /**
     * The constant BLOCK_HEIGHT.
     */
    public static final int BLOCK_HEIGHT = 20;
    /**
     * The constant BALL_SPEED.
     */
    public static final int BALL_SPEED = 5;

    /**
     * Creates the background, the frame blocks and the scorekeeper
     * constituting the base elements of the game. Then, it adds them to the
     * game.
     *
     * @param ballRemover the ball remover listener
     */
    public void createFrame(HitListener ballRemover) {

        Block background = new Block(new Rectangle(new Point(0, 0),
                GUI_WIDTH, GUI_HEIGHT));
        //Choosing a background color using the HSB scale.
        background.setColor(Color.getHSBColor(0.455f, 0.7f, 0.7f));
        Block scorePanel = new Block(new Rectangle(new Point(0, 0),
                800, 20));
        scorePanel.setColor(Color.white);
        Block up = new Block(new Rectangle(new Point(20, 20),
                760, 20));
        up.setColor(Color.gray);
        Block down = new Block(new Rectangle(new Point(20, 580),
                760, 20));
        down.setColor(Color.gray);
        Block left = new Block(new Rectangle(new Point(0, 20),
                20, 600));
        left.setColor(Color.gray);
        Block right = new Block(new Rectangle(new Point(780, 20),
                20, 600));
        right.setColor(Color.gray);

        // Adding all the blocks to the Game. The order they are added in is
        // the ordered in which they are printed.
        background.addToGame(this);
        scorePanel.addToGame(this);
        up.addToGame(this);
        down.addToGameAsDeathZone(this);
        left.addToGame(this);
        right.addToGame(this);

        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);

        down.addHitListener(ballRemover);
    }

    /**
     * Creates a row of blocks of a specified color and number, starting at a
     * specified point and with a specified width and height. The blocks are
     * added to the game.
     * It does that by creating each block in intervals of "width" from the
     * starting point - which is the leftmost point of the row of blocks.
     * For that the blocks have to be identical in shape and size.
     *
     * @param color          the color of the blocks
     * @param numOfBlocks    the number of blocks in the row
     * @param leftStartOfRow the starting point of the row
     * @param blockWidth     the width of all blocks
     * @param blockHeight    the height of all blocks
     * @param blockRemover   the block remover

     */
    public void createRow(Color color, int numOfBlocks, Point leftStartOfRow,
                          int blockWidth, int blockHeight,
                          HitListener blockRemover) {
        ScoreTrackingListener scoreTracker =
                new ScoreTrackingListener(this.score);
        for (int i = 0; i < numOfBlocks; i++) {

            //x difference offsets the distance of each block from its previous.
            //Technically, from the start point.
            int xDifference = i * blockWidth;
            Point startOfBlock =
                    new Point(leftStartOfRow.getX() + xDifference,
                            leftStartOfRow.getY());
            Block current =
                    new Block(new Rectangle(startOfBlock, blockWidth,
                            blockHeight));
            current.setColor(color);
            current.addToGame(this);
            current.addHitListener(blockRemover);
            current.addHitListener(scoreTracker);
        }
    }

    /**
     * Initializes the game. First, a list of colors and a list of block
     * numbers are created to match every row created in the game.
     * Second, the ball is created and is assigned speed and the game
     * environment. The ball is added to the game.
     * Third, each row is created with its matching color and number of
     * blocks from the lists.
     */
    public void initialize() {

        BallRemover ballRemover = new BallRemover(this, this.remainedBalls);
        // setting up the borders and background has to come first.
        createFrame(ballRemover);
        KeyboardSensor keyboard = this.gui.getKeyboardSensor();
        Paddle puddle = new Paddle(keyboard);
        puddle.addToGame(this);

        List<Color> colors = new ArrayList<>();
        colors.add(Color.gray);
        colors.add(Color.red);
        colors.add(Color.yellow);
        colors.add(Color.blue);
        colors.add(Color.pink);
        colors.add(Color.green);

        List<Integer> numOfBlocksInRowI = new ArrayList<>();
        numOfBlocksInRowI.add(ROW_ONE_BLOCK_NUM);
        numOfBlocksInRowI.add(ROW_TWO_BLOCK_NUM);
        numOfBlocksInRowI.add(ROW_THREE_BLOCK_NUM);
        numOfBlocksInRowI.add(ROW_FOUR_BLOCK_NUM);
        numOfBlocksInRowI.add(ROW_FIVE_BLOCK_NUM);
        numOfBlocksInRowI.add(ROW_SIX_BLOCK_NUM);

        // The distance 100 (100*i difference) might be too large if there are
        // too many balls so that a ball is summoned outside the GUI limits.

        Ball[] ballsArray = new Ball[NUM_OF_BALLS];
        double angle = (double) 160 / (NUM_OF_BALLS - 1);
        for (int i = 0; i < NUM_OF_BALLS; i++) {
            ballsArray[i] = new Ball(400, 500, 5, Color.black);
            ballsArray[i].setVelocity(Velocity.fromAngleAndSpeed(100
                            + angle * i, BALL_SPEED));
            ballsArray[i].setGameEnvironment(this.environment);
            ballsArray[i].addToGame(this);
        }

        /*
         * Iterating through the colors list is the same as iterating through
         * all lines, because each line has a (unique) color (a bijection).
         *
         * The point of start for each block is determined using intervals of
         * block width and height, so that each row (which contains one block
         * less), starts one "block" away and below the row above it.
         *
         * Technically, it is done with multiplication of the block row number
         * with the height and weight of one block (which is the same for all
         * blocks) and that is added to the initial location (180,120).
         */

        BlockRemover blockRemover = new BlockRemover(this, this.remainedBlocks);

        for (int i = 0; i < colors.size(); i++) {
            int xDifference = i * BLOCK_WIDTH;
            int yDifference = i * BLOCK_HEIGHT;
            Point leftStartOfRow = new Point(180 + xDifference,
                    120 + yDifference);
            createRow(colors.get(i), numOfBlocksInRowI.get(i), leftStartOfRow,
                    BLOCK_WIDTH, BLOCK_HEIGHT, blockRemover);
        }
    }

    // Run the game -- start the animation loop.

    /**
     * Runs the game by starting an animation loop that draws the sprites and
     * updates their positions by notifying them that time has passed.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = this.gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            this.gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }

            if (this.remainedBalls.getValue() == 0
                    || (this.remainedBlocks.getValue() == 0)) {
                // If the level has been cleared increase the score
                if (this.remainedBlocks.getValue() == 0) {
                    score.increase(100);
                }
                // In either case do:

                // Printing the game board before closing the game in order to
                // let the player see the board, the final score and to have a
                // small hiatus before exiting the game.
                DrawSurface end = this.gui.getDrawSurface();
                this.sprites.drawAllOn(end);
                this.gui.show(end);
                sleeper.sleepFor(300);
                this.gui.close();
            }
        }
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
}
