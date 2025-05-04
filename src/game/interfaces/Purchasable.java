package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface for items that can be purchased.
 * 
 * This interface defines the method that should be implemented by any item
 * that can be purchased in the game. The method is called when the item is
 * purchased, allowing for specific effects to be applied.
 */
public interface Purchasable {
    void uponPurchase(Actor buyer, GameMap map);
}
