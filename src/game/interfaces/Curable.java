package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An interface representing an object that can be cured by an item.
 * 
 * This interface is used to define the behaviour of objects that can be cured
 * Classes implementing this interface must provide the logic for how they are cured.
 */
public interface Curable {
    
    /**
     * Cures the object using the specified item.
     * 
     * This method defines the behaviour of the object when it is cured by an item.
     * The curing process may involve modifying the state of the object, the user,
     * or the game map.
     *
     * @param item The item used for curing
     * @param user The actor performing the curing action
     * @param map  The game map where the curing action takes place
     * @return A string describing the result of the curing action
     */
    String beCuredBy(Item item, Actor user, GameMap map);

}