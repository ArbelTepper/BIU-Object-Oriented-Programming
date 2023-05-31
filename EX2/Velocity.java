//Arbel Tepper 209222272
package EX2;

/**
 * The type Velocity.
 * Velocity specifies the change in position on the `x` and the `y` axis.
 */

public class Velocity {
    private double dx;
    private double dy;

    /**
     * Instantiates a new Velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * From angle and speed creates a velocity object given an angle and speed.
     * It computes the difference in x and in y.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity object.
     */

    // The speed here has to match the speed of the ball when it's created
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * Gets dx.
     *
     * @return the dx
     */
    public double getDx() {
        return dx;
    }

    /**
     * Gets dy.
     *
     * @return the dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * applyToPoint adds the "velocity" i.e. the change in location to a given
     * point.
     *
     * @param p the Point whose X and Y values represent the current location.
     * @return a new Point with the updated X and y values.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}