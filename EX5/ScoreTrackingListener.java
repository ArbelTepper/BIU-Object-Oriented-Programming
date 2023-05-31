// Arbel Tepper 209222272
package EX5;

import EX2.Ball;
import EX3.Block;

/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter from Game
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * hitEvent states what has to be done when a hit is detected.
     *
     * @param beingHit the hit Block
     * @param hitter   the hitting Ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       currentScore.increase(5);
    }
}