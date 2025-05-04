package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface for conditions that determine whether a monologue should be spoken.
 * Implementations of this interface can define specific conditions for monologues.
 */
public interface MonologueCondition {
    boolean test(Actor listener, GameMap map);
}
