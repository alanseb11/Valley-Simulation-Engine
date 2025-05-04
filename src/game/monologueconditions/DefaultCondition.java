package game.monologueconditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.MonologueCondition;

/**
 * A default condition that always returns true.
 * This condition can be used when no specific condition is required.
 */
public class DefaultCondition implements MonologueCondition {

    /**
     * Constructor.
     */
    public DefaultCondition() {
        // No specific initialisation needed for default condition
    }

    /**
     * Checks if the condition is met for the given actor.
     *
     * @param listener The actor to check the condition against
     * @return true 
     */
    @Override
    public boolean test(Actor listener, GameMap map) {
        return true; // Always returns true for default condition
    }

}
