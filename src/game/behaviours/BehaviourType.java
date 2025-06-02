package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.Map;

/**
 * Interface defining how behaviors should be selected for an actor.
 * This follows the Strategy pattern to allow different behavior selection algorithms.
 */
public interface BehaviourType {
    /**
     * Selects and returns an action based on the available behaviors.
     *
     * @param behaviours Map of behaviors with their priorities
     * @param actor The actor performing the behavior
     * @param map The game map
     * @return The selected action, or null if no valid action is found
     */
    Action selectAction(Map<Integer, Behaviour> behaviours, Actor actor, GameMap map);
} 