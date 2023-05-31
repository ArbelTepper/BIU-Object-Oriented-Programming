//Arbel Tepper 209222272
package EX3;

import java.util.ArrayList;
import java.util.List;

/**
 * The CollidableCollection class represents a collection of collidables in the
 * game.
 */
public class CollidableCollection {
    private List<Collidable> collidableList;

    /**
     * Creates a new CollidableCollection with an empty list of collidables.
     */
    public CollidableCollection() {
        this.collidableList = new ArrayList<>();
    }
}
