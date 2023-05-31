// Arbel Tepper 209222272
package EX5;

import EX2.Ball;
import EX3.Block;

/**
 * The interface Hit listener.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */

    void hitEvent(Block beingHit, Ball hitter);
}

