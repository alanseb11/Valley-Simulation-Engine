package game.time;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Ability;
import game.capabilities.Status;

public class Night extends TimeOfDay {
    /**
     * Constructor for Night time of day.
     * Initialises the night with a duration of 1 turn.
     */
    public Night() {
        super("Night", new Countdown(2));
    }

    @Override
    public void applyEffect(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actor.removeCapability(Ability.PURCHASE);
        }

        if (actor.hasCapability(Ability.ATTACK)) {
            actor.addCapability(Status.AGGRESSIVE);
        }
    }

}
