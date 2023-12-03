//Arbel Tepper 209222272
package EX6;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private Animation animation;
    private String key;
    private KeyboardSensor keyboard;
    private boolean isAlreadyPressed;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param keyboard  the keyboard
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboard, String key,
                                      Animation animation) {
        this.animation = animation;
        this.key = key;
        this.keyboard = keyboard;
        this.isAlreadyPressed = true;
    }
    /** Perform one frame of the animation and makes sure the button wasn't
     * pressed before.
     *
     * @param d the DrawSurface to draw on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.keyboard.isPressed(this.key)) {
            if (!isAlreadyPressed) {
                this.isAlreadyPressed = false;
            }
        }
    }

    /**
     * Check if the animation should stop.
     *
     * @return true if the animation should stop, false otherwise
     */
    @Override
    public boolean shouldStop() {
        return this.animation.shouldStop();
    }
}
