package game.time;

import edu.monash.fit2099.engine.actors.Actor;
import game.capabilities.Ability;
import game.capabilities.Status;

/**
 * Represents the morning time of day in the game.
 * Morning lasts for 1 turn and applies specific effects to actors.
 */
public class Morning extends TimeOfDay {
    /**
     * Constructor for Morning time of day.
     * Initialises the morning with a duration of 1 turn.
     */
    public Morning() {
        super("Morning", new Countdown(1));
    }

    /**
     * Applies the effects of morning to the specified actor and game map.
     * In the morning, actors that are hostile to enemies are buffed,
     * and aggressive actors lose their aggressive capability.
     *
     * @param actor the actor to apply the effect to
     */
    public void applyEffect(Actor actor) {
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actor.addCapability(Status.BUFFED);
            actor.removeCapability(Ability.PURCHASE);
        }

        if (actor.hasCapability(Ability.ATTACK)) {
            actor.removeCapability(Status.AGGRESSIVE);
        }
    }
}
