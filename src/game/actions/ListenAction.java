package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Monologuer;

public class ListenAction extends Action {
    private Monologuer monologuer;

    /**
     * Constructor.
     *
     * @param monologue The monologue to be spoken
     */
    public ListenAction(Monologuer monologuer) {
        this.monologuer = monologuer;
    }

    /**
     * Executes the action.
     *
     * @param actor The actor performing the action
     * @param map   The map containing the actor
     * @return A string describing the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return monologuer + ": \"" + monologuer.getMonologue(actor, map) + "\"";
    }

    /**
     * Returns a string describing the action.
     *
     * @param actor The actor performing the action
     * @return A string describing the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " listens to " + monologuer;
    }

}
