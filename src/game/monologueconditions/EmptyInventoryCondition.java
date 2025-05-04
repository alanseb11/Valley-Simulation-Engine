package game.monologueconditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.MonologueCondition;

/**
 * A condition that checks if the actor's inventory is empty.
 * This condition can be used to trigger monologues when the actor has no items.
 */
public class EmptyInventoryCondition implements MonologueCondition {

    /**
     * Constructor.
     */
    public EmptyInventoryCondition() {
        // No initialisation needed
    }

    /**
     * Checks if the actor's inventory is empty.
     *
     * @param listener The Actor to check
     * @param map      The map containing the Actor
     * @return true if the actor's inventory is empty, false otherwise
     */
    @Override
    public boolean test(Actor listener, GameMap map) {
        return listener.getItemInventory().isEmpty();
    }

}
