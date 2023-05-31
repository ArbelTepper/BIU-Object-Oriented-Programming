// Arbel Tepper 209222272
package Unneccesary;

import EX2.Ball;
import EX3.Block;
import EX5.HitListener;

/**
 * The type Printing hit listener.
 */
public class PrintingHitListener implements HitListener {

    /**
     * lets you know a ball was hit.
     *
     * @param beingHit the hit block
     * @param hitter   the hitting ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}
