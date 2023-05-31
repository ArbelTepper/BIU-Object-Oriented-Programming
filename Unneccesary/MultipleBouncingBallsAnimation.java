//Arbel Tepper 209222272
package Unneccesary;

import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;
import biuoop.Sleeper;

/**
 * The type Multiple bouncing balls animation.
 */
public class MultipleBouncingBallsAnimation {
    /**
     * The Speed factor.
     */
    static final int SPEED_FACTOR = 80;
    /**
     * The Gui height.
     */
    static final int GUI_HEIGHT = 300;
    /**
     * The Gui width.
     */
    static final int GUI_WIDTH = 400;
    /**
     * The Degrees.
     */
    static final int DEGREES = 360;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */

    /*
    * public static void main(String[] args) {
        InvalidInput.noArgs(args);

        Random rand = new Random();
        GUI gui = new GUI("Multiple Balls Whole Screen",
                GUI_WIDTH, GUI_HEIGHT);
        Sleeper sleeper = new Sleeper();
        Color color = Animation.randomColor();
        //length of the array of inputs and the following array of balls.
        int len = args.length;
        Ball[] array = new Ball[len];
        for (int i = 0; i < len; i++) {
            // the radius of the ball.
            int size = (int) Double.parseDouble(args[i]);
            size = InvalidInput.positiveRadius(size);
            size = InvalidInput.sizeOfBall(size, Math.min(GUI_WIDTH,
                    GUI_HEIGHT));
            // x0 is the X value of each ball.
            // size is subtracted and then added to prevent the edges of the
            // ball from showing off-screen.
            int x0 = rand.nextInt(GUI_WIDTH - 2 * size - 2) + size + 1;
            int y0 = rand.nextInt(GUI_HEIGHT - 2 * size - 2) + size + 1;
            int angle = rand.nextInt(DEGREES) + 1;
            Velocity velocity = Velocity.fromAngleAndSpeed(angle,
                    SPEED_FACTOR / size);
            array[i] = new Ball(x0, y0, size, color);
            array[i].setVelocity(velocity);
        }
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            // Draws all the balls on the board.
            for (int i = 0; i < len; i++) {
                Animation.drawBall(array[i], d);
            }
            gui.show(d);
            sleeper.sleepFor(50);
        }
    }
    * */


}
