package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.time.Countdown;

/**
 * A behaviour that applies a countdown effect to an actor.
 * If the countdown expires, the actor will perform a specific action.
 */
public class CountdownBehaviour implements Behaviour {
    private Countdown countdown;
    private Action action;

    /**
     * Constructs a CountdownBehaviour with the specified countdown.
     *
     * @param countdown The CountdownDecay object representing the countdown
     * @param action    The action to be performed when the countdown expires
     */
    public CountdownBehaviour(Countdown countdown, Action action) {
        this.countdown = countdown;
        this.action = action;
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
            countdown.resetCountdown();
            return action;
        }
        countdown.applyTo(actor, action.menuDescription(actor));
        return null;
    }

}
