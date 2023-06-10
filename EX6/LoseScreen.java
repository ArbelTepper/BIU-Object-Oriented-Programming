package EX6;

import EX5.Counter;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The PauseScreen class represents a screen displayed when the game is paused.
 * It implements the Animation interface.
 */
public class LoseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;
    /**
     * Constructs a PauseScreen with the specified keyboard sensor.
     *
     * @param k the keyboard sensor used to check for input
     * @param score the keyboard sensor used to check for input
     */
    public LoseScreen(KeyboardSensor k, Counter score) {
        this.keyboard = k;
        this.score = score;
        this.stop = false;
    }
    /**
     * Performs one frame of the animation.
     *
     * @param d the DrawSurface to draw on
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "Game Over. Your score is "
                        + score.getValue(), 32);
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
