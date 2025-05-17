package game.monologueconditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.MonologueCondition;

/**
 * A class representing a monologue that is conditionally spoken by an actor.
 * The monologue is only spoken if the specified condition is met.
 */
public class ConditionalMonologue {
    MonologueCondition condition;
    String monologue;

    /**
     * Constructor.
     *
     * @param condition The condition that must be met for the monologue to be spoken
     * @param monologue The monologue to be spoken
     */
    public ConditionalMonologue(MonologueCondition condition, String monologue) {
        this.condition = condition;
        this.monologue = monologue;
    }

    /**
     * Checks if the condition is met for the given actor.
     *
     * @param actor The actor to check the condition against
     * @return true if the condition is met, false otherwise
     */
    public boolean isApplicableTo(Actor listener, GameMap map) {
        return condition.test(listener, map);
    }

    /**
     * Gets the monologue.
     *
     * @return The monologue
     */
    public String getMonologue() {
        return monologue;
    }

}
