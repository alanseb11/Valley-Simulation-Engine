package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface representing items that can be sold.
 */
public interface Sellable {
    /**
     * A method that's called when an item is sold.
     *
     * @param actor The actor selling the item
     * @param merchant The merchant buying the item
     * @return A string representing the transaction
     */
    String sell(Actor actor, Actor merchant);
}
