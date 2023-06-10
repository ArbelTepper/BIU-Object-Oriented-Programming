//Arbel Tepper 209222272
package Unneccesary;

import EX2.Ball;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;

/**
 * The type Animation.
 */
public class OldAnimation {
    public static final int GUI_HEIGHT = 300,
    /**
     * The Gui height.
     */
    GUI_WIDTH = 400;
    /**
     * The Gui width.
     */

    public static final int BALL_RADIUS = 10;
    /**
     * randomColor returns a random color of Color object.
     *
     * @return the color
     */
    public static Color randomColor() {
        Random random = new Random();
        final float hue = random.nextFloat();
        final float saturation = 0.9f;
        final float luminance = 1.0f;
        return Color.getHSBColor(hue, saturation, luminance);
    }

    /**
     * drawBall receives a Ball object and a surface and draws it on the
     * surface, after moving it one step.
     *
     * @param b the ball
     * @param d the drawing surface
     */



    /**
     * drawBallWithinFrame receives a Ball object and a surface and draws it on
     * the surface, after moving it one step. It does so using the
     * moveOneStepWithinFrame method which keeps the ball within the frame.
     *
     * @param b the ball
     * @param d the drawing surface
     * @param startHeight the Y value of the start point of the frame.
     * @param endHeight the height difference to the end point of the frame.
     * @param startWidth the X value of the start point of the frame.
     * @param endWidth the width difference to the end point of the frame.
     */
    public static void drawBallWithinFrame(Ball b, DrawSurface d,
                                           double startHeight, double startWidth,
                                           double endHeight, double endWidth) {
        b.moveOneStepWithinFrame(startHeight, startWidth, endHeight,
                endWidth);
        b.drawOn(d);
    }
}