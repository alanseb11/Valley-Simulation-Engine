package game.monologueconditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Threshold;
import game.interfaces.MonologueCondition;

/**
 * A condition that checks the health of an actor.
 * This condition can be used to trigger monologues based on the actor's health status.
 */
public class HealthCondition implements MonologueCondition {
    private int healthThreshold;
    private Threshold comparison;

    /**
     * Constructor.
     *
     * @param healthThreshold The health threshold for the condition
     * @param comparison    The threshold type (ABOVE or BELOW)
     */
    public HealthCondition(int healthThreshold, Threshold comparison) {
        this.healthThreshold = healthThreshold;
        this.comparison = comparison;
    }

    @Override
    public boolean test(Actor listener, GameMap map) {
        return comparison.compare(listener.getAttribute(BaseActorAttributes.HEALTH), healthThreshold);
    }

}
