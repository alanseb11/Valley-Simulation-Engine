package game.time;

import edu.monash.fit2099.engine.actors.Actor;
import game.capabilities.Ability;
import game.capabilities.Status;

/**
 * Represents the night time of day in the game.
 * Night lasts for 2 turns and applies specific effects to actors.
 */
public class Night extends TimeOfDay {
    /**
     * Constructor for Night time of day.
     * Initialises the night with a duration of 1 turn.
     */
    public Night() {
        super("Night", new Countdown(2));
    }

    /**
     * Applies the effects of night to the specified actor and game map.
     * In the night, actors that are hostile to enemies lose their purchase ability,
     * and aggressive actors gain their aggressive capability.
     *
     * @param actor the actor to apply the effect to
     */
    @Override
    public void applyEffect(Actor actor) {
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actor.removeCapability(Ability.PURCHASE);
            actor.removeCapability(Status.BUFFED);
        }

        if (actor.hasCapability(Ability.ATTACK)) {
            actor.addCapability(Status.AGGRESSIVE);
        }
    }

}
