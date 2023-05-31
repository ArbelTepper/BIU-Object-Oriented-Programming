//Arbel Tepper 209222272
package EX2;

/**
 * The type Invalid input.
 */
public class InvalidInput {
    /**
     * The constant DEFAULT is used for correction of invalid input.
     */
    public static final int DEFAULT = 5;

    /**
     * Check for invalid entry of an argument in the nonNumeric method.
     * Invalid entry includes: nun-numeric strings.
     * Solution: replacing with the default value.
     *
     * @param input the user-inserted argument value for inspection.
     * @return the approved argument value.
     */
    public static double nonNumeric(String input) {
        double numeric;
        try {
            numeric = Double.parseDouble(input);
        } catch (NumberFormatException nfe) {
            System.out.println("Your non-numeric string input was converted "
                    + "to the default value.");
            return DEFAULT;
        }
        return numeric;
    }

    /**
     * Check for invalid number of arguments to the multipleFrames method.
     * Invalid entry includes: 0 arguments.
     * Solution: notifying the user they have to insert arguments and
     * exiting the program.
     *
     * @return the array of arguments, if there are ones.
     * @param input the user-inserted array of arguments for inspection.
     */
    public static String[] noArgs(String[] input) {
        if (input.length == 0) {
            System.out.println("This class requires you to insert command-line"
                    + " arguments, try again.");
            System.exit(0);
        }
        return input;
    }
    /**
     * Check for invalid number of arguments to the bouncingBalls method.
     * Invalid entry includes: less than 4 arguments.
     * Solution: filling up the missing values with the default value.
     *
     * @param input the user-inserted array of arguments for inspection.
     * @return the approved array of arguments.
     */
    public static String[] invalidNumOfArgs(String[] input) {
        if (input.length < 4) {
            // Copies the existing values to the new array.
            String[] corrected = new String[4];
            for (int i = 0; i < input.length; i++) {
                corrected[i] = input[i];
            }
            // fills in values in the empty places.
            for (int i = input.length; i < 4; i++) {
                corrected[i] = String.valueOf(DEFAULT);
                System.out.println("A missing value was given the default "
                        + "value.");
            }
            return corrected;
        } else {
            return input;
        }
    }

    /**
     * Check for invalid entry of the Radius parameter.
     * Invalid entry includes: 0 or negative integers.
     * Solution: assigning the default value.
     *
     * @param r the user-inserted radius value for inspection.
     * @return the approved radius value.
     */
    public static int positiveRadius(int r) {
        if (r < 1) {
            return DEFAULT;
        } else {
            return r;
        }
    }

    /**
     * Check for invalid entry of the velocity (difference) parameter.
     * Invalid entry includes: 0.
     * Solution: replacing it with the default value.
     *
     * @param difference the user-inserted difference value for inspection.
     * @return the approved difference value.
     */
    public static double velocity(double difference) {
        if (difference == 0) {
            System.out.println("We don't like stagnant balls, the default "
                    + "value has been assigned instead.");
            return DEFAULT;
        } else {
            return difference;
        }
    }


    /**
     * Check whether the X value of the ball is beyond the borders of the frame.
     * Invalid entry includes: values which are over the Gui width value set
     * in the Animation class, or values below 0.
     * Solution: replacing it with a value close to the border but not
     * crossing it.
     *
     * @param point    the point to which the user-inserted X and Y values
     *                 are assigned for inspection.
     * @param radius   the radius of the ball.
     * @param guiWidth the gui width.
     * @return the approved X coordinate value.
     */
    public static double summonClashesWithBorderX(Point point, int radius,
                                                  int guiWidth) {
        if (point.getX() < radius) {
            System.out.println("You tried to summon the ball outside of the "
                    + "frame or on it, it was moved inside.");
            return radius + 1;
        } else if (point.getX() > guiWidth - radius) {
            System.out.println("You tried to summon the ball outside of the "
                    + "frame or on it, it was moved inside.");
            return guiWidth - radius - 1;
        } else {
            return point.getX();
        }
    }

    /**
     * Check whether the Y value of the ball is beyond the borders of the frame.
     * Invalid entry includes: values which are over the Gui height value set
     * in the Animation class, or values below 0.
     * Solution: replacing it with a value close to the border but not
     * crossing it.
     *
     * @param point     the point to which the user-inserted X and Y values
     *                  are assigned for inspection.
     * @param radius    the radius of the ball.
     * @param guiHeight the gui height.
     * @return the approved X coordinate value.
     */
    public static double summonClashesWithBorderY(Point point, int radius,
                                                  int guiHeight) {
        if (point.getY() < radius) {
            System.out.println("You tried to summon the ball outside of the "
                    + "frame or on it, it was moved inside.");
            return radius + 1;
        } else if (point.getY() > guiHeight - radius) {
            System.out.println("You tried to summon the ball outside of the "
                    + "frame or on it, it was moved inside.");
            return guiHeight - radius - 1;
        } else {
            return point.getY();
        }
    }
    /**
     * Check whether the radius value of the ball is over the value of either
     * the height or the width of the frame.
     * Invalid entry includes: values which are over the Gui height and
     * width values.
     * Solution: replacing it with the default value.
     *
     * @param radius    the radius
     * @param maxSize  the gui width
     * @return the approved size of the ball
     */
    public static int sizeOfBall(int radius,  int maxSize) {
        if (maxSize - 2 * radius < 1) {
            System.out.println("Your ball was too big for the frame so its "
                    + "size was assigned the default value.");
            return DEFAULT;
        } else {
            return radius;
        }
    }
}
