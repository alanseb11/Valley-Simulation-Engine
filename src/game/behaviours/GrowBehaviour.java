package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.GrowAction;
import game.interfaces.Growable;

/**
 * A {@link Behaviour} that allows a {@link Growable} object to grow
 * if certain conditions are met (e.g., no nearby enemies).
 *
 * <p>
 * This behaviour is typically used by bosses or special NPCs
 * that can grow new parts or become stronger over time.
 * </p>
 *
 * <p>
 * For example, the Bed of Chaos boss uses this behaviour to grow branches and leaves
 * on its turn when there are no players adjacent.
 * </p>
 *
 * @author Alan Sebastian
 */
public class GrowBehaviour implements Behaviour {
    private final Growable growable;

    /**
     * Constructor for GrowBehaviour.
     *
     * @param growable the target object implementing {@link Growable}
     */
    public GrowBehaviour(Growable growable) {
        this.growable = growable;
    }

    /**
     * Returns an action that allows the growable object to grow if conditions are met.
     *
     * @param actor the actor performing the action
     * @param map   the game map where the action occurs
     * @return a {@link GrowAction} if the object can grow; otherwise null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (growable.canGrow(map)) {
            return new GrowAction(growable);
        }
        return null;
    }

}
