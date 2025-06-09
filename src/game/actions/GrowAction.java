package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Growable;

/**
 * Represents an action that allows a {@link Growable} actor or object
 * to grow a new part (such as a branch or a leaf).
 *
 * <p>
 * This action can be executed by an actor to trigger the growth logic
 * defined within the growable object, enabling dynamic expansion
 * of parts or attributes in-game.
 * </p>
 *
 * <p>
 * Example use case: Bed of Chaos boss growing branches and leaves during its turn.
 * </p>
 *
 * @author Alan Sebastian
 */
public class GrowAction extends Action {
    private final Growable growable;

    /**
     * Constructor for GrowAction.
     *
     * @param growable the target object implementing {@link Growable} that can grow parts
     */
    public GrowAction(Growable growable) {
        this.growable = growable;
    }

    /**
     * Executes the grow action by calling the grow method on the growable object.
     *
     * @param actor the actor performing the grow action
     * @param map   the game map where the action occurs
     * @return a string message describing the result of the growth
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return growable.grow();
    }

    /**
     * Returns a description of the grow action for display in the action menu.
     *
     * @param actor the actor performing the action
     * @return a string description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " grows a new branch or leaf";
    }
}
