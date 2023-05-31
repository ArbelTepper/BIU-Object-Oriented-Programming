//Arbel Tepper 209222272
package EX2;

/**
 * The type Point.
 */
public class Point {

    static final double COMPARISON_THRESHOLD = 0.00001;
    private double x;
    private double y;

    /**
     * Instantiates a new Point.
     *
     * @param x the x value of the point.
     * @param y the y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets x.
     *
     * @return the x value of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y value of the point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets x.
     *
     * @param newX the new x
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * Sets y.
     *
     * @param newY the new y
     */
    public void setY(double newY) {
        this.y = newY;
    }
    /**
     * Distance calculates the distance from the point it was operated on to
     * a point it was given.
     *
     * @param other the point to which the distance is measured
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        double otherX = other.getX();
        double otherY = other.getY();

        if (this.equals(other)) {
            return 0;
        } else if (otherX == this.x) {
            return Math.abs(otherY - this.y);
        } else if (otherY == this.y) {
            return Math.abs(otherX - this.x);
        } else {
            return Math.sqrt(Math.pow(otherX - this.x, 2)
                    + Math.pow(otherY - this.y, 2));
        }
    }

    /**
     * Equals checks if 2 points have the same coordinates. It uses a
     * comparison threshold to compare doubles.
     *
     * @param other the point whose values are compared with
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        double otherX = other.getX();
        double otherY = other.getY();

        return Math.abs(otherX - this.x) < COMPARISON_THRESHOLD
                && Math.abs(otherY - this.y) < COMPARISON_THRESHOLD;
    }
}