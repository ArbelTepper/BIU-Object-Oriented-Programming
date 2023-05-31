//Arbel Tepper 209222272
package EX3;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The SpriteCollection class represents a collection of Sprite objects.
 */
public class SpriteCollection {
    private List<Sprite> spriteList;

    /**
     * Instantiates a new Sprite collection.
     */

    public SpriteCollection() {
        this.spriteList = new ArrayList<>();
    }

    /**
     * Adds a Sprite object to the SpriteCollection.
     *
     * @param s the Sprite object to add to the collection.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * Calls the timePassed() method on all Sprites in the collection.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites = new ArrayList<Sprite>(this.spriteList);
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }

    /**
     * Calls the drawOn(d) method on all Sprites in the collection.
     *
     * @param d the DrawSurface on which to draw the Sprites.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> sprites = new ArrayList<Sprite>(this.spriteList);
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }

    /**
     * Remove sprite.
     *
     * @param s the sprite to be removed
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }
}
