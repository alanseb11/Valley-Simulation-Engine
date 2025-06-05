package game.time;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Ability;
import game.capabilities.Status;

/**
 * Represents the afternoon time of day in the game.
 * Afternoon lasts for 1 turn and applies specific effects to actors.
 */
public class Afternoon extends TimeOfDay {
    /**
     * Constructor for Afternoon time of day.
     * Initialises the afternoon with a duration of 1 turn.
     */
    public Afternoon() {
        super("Afternoon", new Countdown(1));
    }

    /**
     * Applies the effects of afternoon to the specified actor and game map.
     * In the afternoon, actors that are hostile to enemies can purchase items,
     * and buffed actors lose their buffed capability.
     *
     * @param actor the actor to apply the effect to
     * @param map   the game map where the effect is applied
     */
    @Override
    public void applyEffect(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actor.addCapability(Ability.PURCHASE);
            actor.removeCapability(Status.BUFFED);
        }

        if (actor.hasCapability(Status.NON_HOSTILE_TO_ENEMY)) {
            actor.removeCapability(Status.BUFFED);
        }
    }

}
