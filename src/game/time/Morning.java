package game.time;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Ability;
import game.capabilities.Status;

public class Morning extends TimeOfDay {
    /**
     * Constructor for Morning time of day.
     * Initialises the morning with a duration of 1 turn.
     */
    public Morning() {
        super("Morning", new Countdown(1));
    }

    public void applyEffect(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actor.removeCapability(Ability.PURCHASE);
        }

        if (actor.hasCapability(Ability.ATTACK)) {
            actor.removeCapability(Status.AGGRESSIVE);
        }
    }
}
