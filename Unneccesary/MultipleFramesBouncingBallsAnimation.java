//Arbel Tepper 209222272
package Unneccesary;

import EX2.Ball;
import EX2.InvalidInput;
import EX2.Velocity;
import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;
import biuoop.Sleeper;

/**
 * The type Multiple frames bouncing balls animation.
 */
public class MultipleFramesBouncingBallsAnimation {
    public static final int SPEED_FACTOR = 80;
    /**
     * The Gui height and width.
     */
    public static final int GUI_HEIGHT = 600,
    /**
     * The Gui width.
     */
    GUI_WIDTH = 700;
    /**
     * The Degrees.
     */
    public static final int DEGREES = 360;
    /**
     * The constant GRAY_START.
     */
    public static final int GRAY_START = 50,
    /**
     * The Gray end.
     */
    GRAY_END = 500,
    /**
     * The Gray frame difference.
     */
    GRAY_DIFFERENCE = GRAY_END - GRAY_START;
    /**
     * The constant YELLOW_START.
     */
    public static final int YELLOW_START = 450, /**
     * The Yellow end.
     */
    YELLOW_END = 600,
    /**
     * The Yellow frame difference.
     */
    YELLOW_DIFFERENCE = YELLOW_END - YELLOW_START;

    /**
     * Multiple frames.
     *
     * @param sizes the sizes
     */
    public static void multipleFrames(String[] sizes) {
        sizes = InvalidInput.noArgs(sizes);
        Random rand = new Random();
        GUI gui = new GUI("Multiple Balls Multiple Frames", GUI_WIDTH,
                GUI_HEIGHT);
        Sleeper sleeper = new Sleeper();
        Color color;
        int len = sizes.length;
        Ball[] grays = new Ball[len / 2];
        Ball[] yellows = new Ball[len / 2];
        // the first half of the sizes array.
        for (int i = 0; i < len / 2; i++) {
            // the radius of the ball.
            int size = (int) InvalidInput.nonNumeric(sizes[i]);
            size = InvalidInput.positiveRadius(size);
            size = InvalidInput.sizeOfBall(size, GRAY_DIFFERENCE);
            // 2*size is the diameter of the ball.
            // the -4 makes sure the ball does not exceed the frame beyond
            //     the right and bottom borders.
            // the +1 makes sure the ball does not exceed the frame beyond
            //     the left and upper borders.
            int x0 = rand.nextInt(GRAY_DIFFERENCE - 2 * size - 4)
                    + size + GRAY_START + 1;
            int y0 = rand.nextInt(GRAY_DIFFERENCE - 2 * size - 4)
                    + size + GRAY_START + 1;
            color = OldAnimation.randomColor();
            int angle = rand.nextInt(DEGREES) + 1;
            Velocity velocity = Velocity.fromAngleAndSpeed(angle,
                    SPEED_FACTOR / size);
            grays[i] = new Ball(x0, y0, size, color);
            grays[i].setVelocity(velocity);
        }
        // the second half of the sizes array.
        for (int j = 0; j < len / 2; j++) {
            int size = (int) InvalidInput.nonNumeric(sizes[j + len / 2]);
            size = InvalidInput.positiveRadius(size);
            size = InvalidInput.sizeOfBall(size, YELLOW_DIFFERENCE);
            int x0 = rand.nextInt(YELLOW_DIFFERENCE - 2 * size - 4)//
                    + size + YELLOW_START + 1; //
            int y0 = rand.nextInt(YELLOW_DIFFERENCE - 2 * size - 4)//
                    + size + YELLOW_START + 1;
            color = OldAnimation.randomColor();
            int angle = rand.nextInt(DEGREES) + 1;
            Velocity velocity = Velocity.fromAngleAndSpeed(angle,
                    SPEED_FACTOR / size);
            yellows[j] = new Ball(x0, y0, size, color);
            yellows[j].setVelocity(velocity);
        }
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.gray);
            d.fillRectangle(GRAY_START, GRAY_START,
                    GRAY_DIFFERENCE, GRAY_DIFFERENCE);
            for (int i = 0; i < len / 2; i++) {
                OldAnimation.drawBallWithinFrame(grays[i], d, GRAY_START,
                        GRAY_START, GRAY_END, GRAY_END);
            }
            d.setColor(Color.yellow);
            d.fillRectangle(YELLOW_START, YELLOW_START,
                    YELLOW_DIFFERENCE, YELLOW_DIFFERENCE);
            // Draws all the balls on the board.
            for (int i = 0; i < len / 2; i++) {
                OldAnimation.drawBallWithinFrame(yellows[i], d, YELLOW_START,
                        YELLOW_START, YELLOW_END, YELLOW_END);
            }
            gui.show(d);
            sleeper.sleepFor(50);
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        multipleFrames(args);
    }
}
