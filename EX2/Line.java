//Arbel Tepper 209222272
package EX2;

import EX3.Rectangle;

import java.util.Objects;

/**
 * The type Line.
 */
public class Line {
    /**
     * The constant COMPARISON_THRESHOLD holds the accuracy value for
     * comparing doubles.
     */
    static final double COMPARISON_THRESHOLD = 0.00001;
    private Point start;
    private Point end;
    private Double length;
    private Double slope;
    private Double yIntercept;
    private boolean vertical = false;
    private boolean horizontal = false;

    /**
     * Instantiates a new Line using 2 Point objects.
     *
     * @param start the start point of the line.
     * @param end   the end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.length = this.start.distance(this.end);

        if (Math.abs(end.getX() - start.getX()) < COMPARISON_THRESHOLD) {
            this.slope = null;
        } else {
            this.slope = (end.getY() - start.getY())
                    / (end.getX() - start.getX());
        }

        if (slope == null) {
            this.yIntercept = null;
            this.vertical = true;
        } else {
            if (slope == 0) {
                this.horizontal = true;
            }
            this.yIntercept = this.start.getY() - slope * this.start.getX();
        }
    }

    /**
     * Instantiates a new Line using 4 values.
     *
     * @param x1 the x value of the start Point.
     * @param y1 the y value of the start Point.
     * @param x2 the x value of the end Point.
     * @param y2 the y value of the end Point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.length = this.start.distance(this.end);

        if (end.getX() - start.getX() == 0) {
            this.slope = null;
        } else {
            this.slope = (end.getY() - start.getY())
                        / (end.getX() - start.getX());
        }

        if (slope == null) {
            this.yIntercept = null;
        } else {
            this.yIntercept = this.start.getY() - slope * this.start.getX();
        }
    }

    /**
     * Gets slope.
     *
     * @return the slope
     */
    public Double getSlope() {
        return this.slope;
    }

    /**
     * Length - returns the length of the line.
     *
     * @return the value of Length member.
     */
    public Double getlength() {
        return this.length;
    }

    /**
     * Middle -calculates and returns the middle point of the line.
     * It does it by adding half of the difference in x and y values of the 2
     * points to the values of the first point.
     *
     * @return the middle point of the line.
     */
//
    public Point middle() {

        Double middleX = (this.start.getX() + this.end.getX()) / 2;
        Double middleY = (this.start.getY() + this.end.getY()) / 2;

        return new Point(middleX, middleY);
    }

    /**
     * Start point.
     *
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * End point.
     *
     * @return the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * OnLine receives 3 points and checks whether the second point is on the
     * line made by the first and third points.
     *
     * @param first  the start point of the line.
     * @param second the point tested if it is on the line or not.
     * @param third  the end point of the line.
     * @return boolean answer to the question.
     */

    private static boolean onLine(Point first, Point second, Point third) {
        // If the x value of the second point is within the x values of the
        // first and third points.
        if (second.getX() <= Math.max(first.getX(), third.getX())
                && second.getX() >= Math.min(first.getX(), third.getX())
                // If the y value of the second point is within the y values
                // of the first and third points.
                && second.getY() <= Math.max(first.getY(), third.getY())
                && second.getY() >= Math.min(first.getY(), third.getY())) {
            return true;
        }
        return false;
    }

    /**
     * checkOrientation calculates the orientation of 3 points in 2D space.
     * It does it using the determinant of the vectors made by the points.
     *
     * @param first  the start point of the line.
     * @param second the end point of the line.
     * @param third  the point whose orientation in relation to the line is
     *               checked.
     * @return 0 if the orientation is collinear, 1 if it is clockwise and -1
     * if counter-clockwise.
     */
    private static int checkOrientation(Point first, Point second,
                                        Point third) {
        // Calculation of the determinant of the vectors made by the 2 points.
        Double orientation =
                (((second.getY() - first.getY())
                        * (third.getX() - second.getX()))
                        - ((second.getX() - first.getX())
                        * (third.getY() - second.getY())));

        if (orientation == 0) {
            return 0;
        } else if (orientation > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * checks whether a point is on the line.
     *
     * @param point the point
     * @return true or false.
     */
    public boolean pointOnLine(Point point) {
        // if the line is parallel to the X axes.
        //if (this.getSlope() == null) {
        //    if (point.getX() == this.start.getX()){
        //        if ()
        //    }
        // }
        if (Math.abs(point.getY() - (this.slope * point.getX()
                + this.yIntercept)) <= COMPARISON_THRESHOLD) {
            return true;
        }
        return false;
    }

    /**
     * Is intersecting checks if 2 lines intersect using the
     * checkOrientation and the onLine methods.
     * The lines intersect if orientations 1 and 2, and 3 and 4 share the
     * same orientation, as long as each pair's is different from that of the
     * other and are non-zero.
     * if one orientation is 0, the lines intersect if one point is on the
     * line made by the other 2.
     *
     * @param other the line against which the check is performed.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {

        int ori1 = checkOrientation(this.start, this.end, other.start);
        int ori2 = checkOrientation(this.start, this.end, other.end);
        int ori3 = checkOrientation(other.start, other.end, this.start);
        int ori4 = checkOrientation(other.start, other.end, this.end);


        if (ori1 != ori2 && ori3 != ori4) {
            return true;
        }
        if (ori1 == 0 && onLine(this.start, other.start, this.end)) {
            return true;
        }
        if (ori2 == 0 && onLine(this.start, other.end, this.end)) {
            return true;
        }
        if (ori3 == 0 && onLine(other.start, this.start, other.end)) {
            return true;
        }
        if (ori4 == 0 && onLine(other.start, this.end, other.end)) {
            return true;
        }
        return false;
    }

    /**
     * This method returns the intersection point of this line with the
     * specified line, or null if the lines do not intersect or if they overlap.
     *
     * @param other the other line with which to check for intersection
     * @return the intersection point of this line with the specified line,
     * or null if the lines do not intersect or overlap
     */

    public Point intersectionWith(Line other) {
        // if both lines are the same line
        if (this.equals(other)) {
            return null;
            // if the lines do not intersect
        } else if (!this.isIntersecting(other)) {
            return null;
            // if their slopes are equal - meaning they overlap
        } else if (Objects.equals(this.getSlope(), other.getSlope())) {
            return null;
            // if this line ends where the other starts
        } else if (this.end.equals(other.start)) {
            return this.end;
            // if this line starts where the other ends
        } else if (this.start.equals(other.end)) {
            return this.start;
            // if this line is parallel to the y-axis
        } else if (this.getSlope() == null) {
            Double x = this.start.getX();
            Double y = other.getSlope() * x + other.yIntercept;
            return new Point(x, y);
            // if the other line is parallel to the y-axis
        } else if (other.getSlope() == null) {
            Double x = other.start.getX();
            Double y = this.getSlope() * x + this.yIntercept;
            return new Point(x, y);
        } else {
            Double x = (other.yIntercept - this.yIntercept)
                    / (this.getSlope() - other.getSlope());
            Double y = this.getSlope() * x + this.yIntercept;
            return new Point(x, y);
        }
    }

    /**
     * Equals boolean.
     *
     * @param other the line against which the check is performed.
     * @return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return ((this.start.equals(other.start) && (this.end.equals(other.end)))
                || (this.start.equals(other.end))
                && (this.end.equals(other.start)));
    }

    /**
     * Returns the closest intersection point of this line with a given
     * rectangle to the start of the line.
     * If there is no intersection, return null.
     * If there is only one intersection, return it.
     * If there are two intersections, return the closest one to the start of
     * the line.
     *
     * @param rect the rectangle to check for intersection with.
     * @return the closest intersection point to the start of the line if
     * exists, null otherwise.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> list = rect.intersectionPoints(this);
        if (list.size() == 0) {
            return null;
        } else if (list.size() == 1) {
            return list.get(0);
        } else {
            if (this.start.distance(list.get(0))
                    < this.start.distance(list.get(1))) {
                return list.get(0);
            } else {
                return list.get(1);
            }
        }
    }
}