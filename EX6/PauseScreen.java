//Arbel Tepper 209222272
package EX6;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The PauseScreen class represents a screen displayed when the game is paused.
 * It implements the Animation interface.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    /**
     * Constructs a PauseScreen with the specified keyboard sensor.
     *
     * @param k the keyboard sensor used to check for input
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
    /**
     * Performs one frame of the animation.
     *
     * @param d the DrawSurface to draw on
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
       }
    }
    /**
     * Check if the animation should stop.
     *
     * @return true if the animation should stop, false otherwise
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
