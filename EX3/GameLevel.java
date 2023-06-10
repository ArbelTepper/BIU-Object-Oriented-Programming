//Arbel Tepper 209222272
package EX3;

import EX2.Ball;
import EX2.Point;


import EX5.Counter;

import EX5.ScoreIndicator;
import EX5.ScoreTrackingListener;
import EX5.BallRemover;
import EX5.BlockRemover;

import EX6.Animation;
import EX6.AnimationRunner;
import EX6.LevelInformation;
import EX6.LevelIndicator;
import EX6.PauseScreen;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.KeyboardSensor;

import java.awt.Color;


/**
 * This class represents a game object. It contains a collection of sprites,
 * a game environment, and a graphical user interface. The game involves the
 * creation of blocks and a ball which are added to the game. The ball
 * bounces around and hits the blocks.
 */
public class GameLevel implements Animation {

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter remainedBlocks;
    private Counter remainedBalls;
    private Counter score;
    private Sleeper sleeper;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;

    /**
     * Constructs a new GameLevel with the specified level information, score counter,
     * animation runner, GUI, keyboard sensor, remained blocks counter, and remained balls counter.
     *
     * @param levelInformation the level information for the game level
     * @param score the counter for the score
     * @param runner the animation runner
     * @param gui the GUI
     * @param keyboard the keyboard sensor
     * @param remainedBlocks the counter for the remaining blocks
     * @param remainedBalls the counter for the remaining balls
     */
    public GameLevel(LevelInformation levelInformation, Counter score,
                     AnimationRunner runner, GUI gui,
                     KeyboardSensor keyboard, Counter remainedBlocks,
                     Counter remainedBalls) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = gui;
        this.levelInformation = levelInformation;
        this.score = score;
        this.sleeper = new Sleeper();
        this.runner = runner;
        this.keyboard = keyboard;
        this.remainedBlocks = remainedBlocks;
        this.remainedBalls = remainedBalls;
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
     */
    public void createFrame() {
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
        // Adding all the Background parts to the game.
        for (int i = 0; i < this.levelInformation.getBackground().size(); i++) {
            this.levelInformation.getBackground().get(i).addToGame(this);
        }
        scorePanel.addToGame(this);
        up.addToGame(this);
        down.addToGameAsDeathZone(this);
        left.addToGame(this);
        right.addToGame(this);

        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);

        LevelIndicator levelIndicator =
                new LevelIndicator(this.levelInformation.levelName());
        levelIndicator.addToGame(this);

        BallRemover ballRemover = new BallRemover(this, this.remainedBalls);
        down.addHitListener(ballRemover);
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
        // setting up the borders and background has to come first.
        createFrame();
        Paddle puddle = new Paddle(this.keyboard,
                this.levelInformation.paddleSpeed(),
                this.levelInformation.paddleWidth());
        puddle.addToGame(this);

        Ball[] ballsArray = new Ball[this.levelInformation.numberOfBalls()];
        for (int i = 0; i < ballsArray.length; i++) {
            ballsArray[i] = new Ball(400, 500, 5, Color.black);
            ballsArray[i].setVelocity(this.levelInformation.
                    initialBallVelocities().get(i));
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
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(this.score);
        BlockRemover blockRemover = new BlockRemover(this, this.remainedBlocks);

        for (int i = 0; i < this.levelInformation.blocks().size(); i++) {
            //add listeners to each block and add them to the game
            Block current = this.levelInformation.blocks().get(i);
            current.addHitListener(blockRemover);
            current.addHitListener(scoreTracker);
            current.addToGame(this);
        }
    }


    /**
     * Runs the game by starting an animation loop that draws the sprites and
     * updates their positions by notifying them that time has passed.
     */
    /*
    * public void run() {
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
                //this.gui.close();
            }
        }
    }
    * */
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

    /**
     * Checks if the animation should stop.
     *
     * @return true if the animation should stop, false otherwise
     */
    public boolean shouldStop() {
        return !this.running;
    }
    /**
     * Performs one frame of the animation.
     * Here, it checks if the P button was pressed and runs the game.
     *
     * @param d the DrawSurface to draw on
     */
    public void doOneFrame(DrawSurface d) {
            this.sprites.drawAllOn(d);
            this.sprites.notifyAllTimePassed();

            PauseScreen pauseScreen = new PauseScreen(this.keyboard);
            if (this.keyboard.isPressed("p")) {
                this.runner.run(pauseScreen);
            }


            if (this.remainedBalls.getValue() == 0
                    || (this.remainedBlocks.getValue() == 0)) {
                // If the level has been cleared increase the score
                if (this.remainedBlocks.getValue() == 0) {
                    score.increase(100);
                }

                // Printing the game board before closing the game in order to
                // let the player see the board, the final score and to have a
                // small hiatus before exiting the game.

                DrawSurface end = this.gui.getDrawSurface();
                this.sprites.drawAllOn(end);
                this.gui.show(end);
                sleeper.sleepFor(200);
                this.running = false;
                //gui.close();
            }
    }
    /**
     * Runs the game.
     */
    public void run() {
        //this.createBallsOnTopOfPaddle(); // or a similar method
        this.running = true;
        this.runner.run(this);
    }
}
