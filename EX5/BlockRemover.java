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
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param game           the game
     * @param remainedBlocks the remained blocks counter
     */
    public BlockRemover(Game game, Counter remainedBlocks) {
        this.game = game;
        this.remainingBlocks = remainedBlocks;
    }


    /**
     * Implements the hitEvent method from the HitListener interface
     * Blocks that are hit are removed from the game.
     * Including removing their listener from the game.
     *
     * @param beingHit the hit block
     * @param hitter   the hitting ball
     */

    public void hitEvent(Block beingHit, Ball hitter) {
       beingHit.removeFromGame(game);
       //beingHit.removeHitListener(this);/
        //removes the ScoreTracking listener from the block which is entered
        // second
       beingHit.removeHitListener(beingHit.getHitListeners().get(1));
        //removes the BlockRemover listener from the block which is entered
        // first
       beingHit.removeHitListener(beingHit.getHitListeners().get(0));
       this.remainingBlocks.decrease(1);
    }
}