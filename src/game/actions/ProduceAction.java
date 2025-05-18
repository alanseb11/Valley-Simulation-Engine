package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Producible;

/**
 * Represents an action where a {@link Producible} produces an offspring.
 */
public class ProduceAction extends Action {
    private final Producible producible;

    /**
     * Constructor.
     *
     * @param producible The actor which can produce.
     */
    public ProduceAction(Producible producible) {
        this.producible = producible;
    }

    /**
     * Executes the action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string describing the result of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return producible.produce(map);
    }

    /**
     * Returns a string describing the action.
     *
     * @param actor The actor performing the action.
     * @return A string describing the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " produces.";
    }
}
