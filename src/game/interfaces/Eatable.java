package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An interface for game entities that can be consumed or eaten by an {@link Actor}.
 * <p>
 * Implementing this interface allows the object to define custom behaviour when
 * consumed, such as healing the actor, providing resources, or removing the item from the map.
 * </p>
 *
 * <p>Examples of implementing classes might include consumable creatures (e.g., Golden Beetle)
 * or items (e.g., Golden Egg).</p>
 */
public interface Eatable {
    /**
     * Defines what happens when an {@link Actor} consumes this object.
     *
     * @param actor the actor that is consuming the object
     * @param map   the current game map where the action is taking place
     * @return a descriptive String detailing the result of the consumption
     */
    String eatenBy(Actor actor, GameMap map);

}
