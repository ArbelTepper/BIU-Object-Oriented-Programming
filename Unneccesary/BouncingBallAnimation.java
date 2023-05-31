//Arbel Tepper 209222272
package Unneccesary;


import EX2.InvalidInput;
import EX2.Point;

/**
 * The type Bouncing ball animation.
 */
public class BouncingBallAnimation {
    /**
     * Animate ball.
     *
     * @param arguments the arguments
     */
    public static void animateBall(String[] arguments) {
        arguments = InvalidInput.invalidNumOfArgs(arguments);
        double x = InvalidInput.nonNumeric(arguments[0]);
        double y = InvalidInput.nonNumeric(arguments[1]);
        double dx = InvalidInput.nonNumeric(arguments[2]);
        double dy = InvalidInput.nonNumeric(arguments[3]);
        Point current = new Point(x, y);
        dx = InvalidInput.velocity(dx);
        dy = InvalidInput.velocity(dy);
        //Animation.drawAnimation(current, dx, dy);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        //animateBall(args);
    }
}
