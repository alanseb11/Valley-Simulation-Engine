package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public interface Merchant {
    /**
     * Method to be called when an item is purchased.
     *
     * @param buyer The Actor that is buying the item
     * @param map   The map containing the Actor
     */
    String uponPurchase(Actor buyer, GameMap map);
}
