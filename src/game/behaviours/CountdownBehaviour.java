package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.CountdownDecay;
import game.actions.UnconsciousAction;

/**
 * A behaviour that applies a countdown effect to an actor.
 * If the countdown expires, the actor becomes unconscious.
 */
public class CountdownBehaviour implements Behaviour {
    private CountdownDecay countdown;

    /**
     * Constructs a CountdownBehaviour with the specified countdown.
     *
     * @param countdown The CountdownDecay object representing the countdown
     */
    public CountdownBehaviour(CountdownDecay countdown) {
        this.countdown = countdown;
    }

    /**
     * Returns the action to be performed by the actor.
     * If the countdown has expired, the actor becomes unconscious.
     *
     * @param actor The actor performing the action
     * @param map   The map where the action takes place
     * @return The action to be performed, or null if no action is available
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (countdown.isExpired()) {
            return new UnconsciousAction();
        }
        countdown.applyTo(actor);
        return null;
    }

}
