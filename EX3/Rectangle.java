//Arbel Tepper 209222272
package EX3;

import EX2.Point;
import EX2.Line;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;

/**
 * The Rectangle class represents a rectangle in a two-dimensional coordinate
 * system. It is defined by an upper-left point, a width, and a height.
 * The class provides methods to calculate intersection points with a line
 * and to draw/fill the rectangle.
 */
public class Rectangle {
    private double width;
    private double height;
    private Point upperLeft;
    private Point upperRight;
    private Point bottomLeft;
    private Point bottomRight;
    private Line up;
    private Line down;
    private Line left;
    private Line right;


    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft upper-left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        double x = this.upperLeft.getX();
        double y = this.upperLeft.getY();
        // Calculate the other three corners of the Rectangle
        this.upperRight = new Point(x + this.width, y);
        this.bottomLeft = new Point(x, y + this.height);
        this.bottomRight = new Point(x + this.width, y + this.height);
        this.up = new Line(upperLeft, upperRight);
        this.down = new Line(bottomLeft, bottomRight);
        this.left = new Line(upperLeft, bottomLeft);
        this.right = new Line(upperRight, bottomRight);
    }

    /**
     * IntersectionPoints finds the points with which a given line intersects
     * with a rectangle. The Maximum number of points is 2.
     *
     * @param line the line for testing
     * @return the list with the intersection points (could be an empty list).
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> list = new ArrayList<>();

        Line[] lines = new Line[4];

        lines[0] = this.getUp();
        lines[1] = this.getDown();
        lines[2] = this.getLeft();
        lines[3] = this.getRight();

        for (int i = 0; i < 4; i++) {
            Point collision = line.intersectionWith(lines[i]);
            if (collision != null) {
                list.add(collision);
            }
        }
        if (list.size() == 2 && list.get(0).equals(list.get(1))) {
            list.remove(1);
        }
        return list;
    }

    /**
     * Gets the width of this rectangle.
     *
     * @return the width
     */
// Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets the height of this rectangle.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets the upper line of this rectangle.
     *
     * @return the upper line
     */
    public Line getUp() {
        return this.up;
    }

    /**
     * Gets the bottom line of this rectangle.
     *
     * @return the bottom line
     */
    public Line getDown() {
        return this.down;
    }

    /**
     * Gets the left line of this rectangle.
     *
     * @return the left line
     */
    public Line getLeft() {
        return this.left;
    }

    /**
     * Gets the right line of this rectangle.
     *
     * @return the right line
     */
    public Line getRight() {
        return this.right;
    }

    /**
     * Fills the rectangle with the color of the DrawSurface object.
     *
     * @param d the DrawSurface object to fill the rectangle on.
     */
    public void fillRectangle(DrawSurface d) {
        d.fillRectangle((int) this.upperLeft.getX(),
                (int) this.upperLeft.getY(),
                (int) this.getWidth(),
                (int) this.getHeight());
    }

    /**
     * Draws the outline of the rectangle on the DrawSurface object with a
     * black color.
     *
     * @param d the DrawSurface object to draw the rectangle on.
     */
    public void drawRectangle(DrawSurface d) {
        d.setColor(Color.black);
        d.drawRectangle((int) this.upperLeft.getX(),
                (int) this.upperLeft.getY(),
                (int) this.getWidth(),
                (int) this.getHeight());
    }
    /**
     * getUpperLeft.
     *
     * @return the upper left point of this rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * getUpperRight.
     *
     * @return the upper right point of this rectangle
     */
    public Point getUpperRight() {
        return this.upperRight;
    }
}