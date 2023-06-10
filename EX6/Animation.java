package EX6;

import biuoop.DrawSurface;
/**
 * The Animation interface represents an animation.
 * It provides methods for performing one frame of the animation and
 * checking if the animation should stop.
 */
public interface Animation {
    /**
     * Perform one frame of the animation.
     *
     * @param d the DrawSurface to draw on
     */
    void doOneFrame(DrawSurface d);
    /**
     * Check if the animation should stop.
     *
     * @return true if the animation should stop, false otherwise
     */
    boolean shouldStop();
}