//Arbel Tepper 209222272
package Unneccesary;

import EX2.Line;
import EX2.Point;
import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * The type Abstract Art Drawing.
 */
public class AbstractArtDrawing {

    /**
     * generateRandomLine generates a random line within the defined limits
     * of X and Y.
     *
     * @return the line
     */
    public static Line generateRandomLine() {
        Random rand = new Random();
        int x1 = rand.nextInt(400) + 1;
        int y1 = rand.nextInt(300) + 1;
        int x2 = rand.nextInt(400) + 1;
        int y2 = rand.nextInt(300) + 1;
        return new Line(x1, y1, x2, y2);
    }

    /**
     * Draws a line in black with rounded values of X and Y.
     * Draws in blue the middle point of the line with rounded values as well.
     *
     * @param l the inserted line.
     * @param d the drawing surface.
     */
    public static void drawLine(Line l, DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawLine((int) Math.round(l.start().getX()),
                   (int) Math.round(l.start().getY()),
                   (int) Math.round(l.end().getX()),
                   (int) Math.round(l.end().getY()));
        d.setColor(Color.BLUE);
        Point middle = l.middle();
        d.fillCircle((int) Math.round(middle.getX()),
                     (int) Math.round(middle.getY()),
                  3);
    }

    /**
     * Draw collision checks if 2 lines collide, if so it draws in red the
     * collision point with rounded values of X and Y.
     *
     * @param l1 line number 1.
     * @param l2 line number 2.
     * @param d  the drawing surface.
     */
    public static void drawCollision(Line l1, Line l2, DrawSurface d) {
        if (l1.isIntersecting(l2)) {
            d.setColor(Color.RED);
            Point intersection = l1.intersectionWith(l2);
            if (intersection != null) {
                d.fillCircle((int) Math.round(intersection.getX()),
                             (int) Math.round(intersection.getY()), 3);
            }
        }
    }

    /**
     * drawLinesAndCircles draws 10 black lines and circles for the collision
     * and middle points of lines, using the generateRandomLine, drawLine and
     * drawCollision methods.
     */
    public static void drawLinesAndCircles() {
        GUI gui = new GUI("My Output", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        Line[] array = new Line[10];
        for (int i = 0; i < 10; i++) {
            array[i] = generateRandomLine();
        }
        for (int i = 0; i < 10; i++) {
            AbstractArtDrawing.drawLine(array[i], d);
            for (int j = 0; j < 10; j++) {
                if (i != j) {
                    AbstractArtDrawing.drawCollision(array[i], array[j], d);
                }
            }
        }
        gui.show(d);
    }
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        drawLinesAndCircles();
    }
}