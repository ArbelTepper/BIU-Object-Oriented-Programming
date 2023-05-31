// Arbel Tepper 209222272
package EX5;

import EX2.Ball;
import EX3.Block;
import EX3.Game;

/**
 * The type Block remover.
 * A BlockRemover is in charge of removing blocks from the game, as well as
 * keeping count of the number of blocks that remain.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Instantiates a new Block remover.
     *
     * @param game           the game
     * @param remainingBalls the remained balls counter
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }


    /**
     * Implements the hitEvent method from the HitListener interface
     * Balls that hit the death-zone are removed from the game.
     * Including removing their listener from the game.
     *
     * @param beingHit the hit block
     * @param hitter   the hitting ball
     */

    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        this.remainingBalls.decrease(1);
    }
}