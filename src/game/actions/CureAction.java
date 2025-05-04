package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Curable;

/**
 * An action that allows an {@link Actor} to use an {@link Item} to cure a {@link Curable} target.
 */
public class CureAction extends Action {
    private Item item;
    private Curable target;

    /**
     * Constructor.
     *
     * @param item   The item to be used for curing
     * @param target The target to be cured
     */
    public CureAction(Item item, Curable target) {
        this.item = item;
        this.target = target;
    }

    /**
     * Executes the cure action.
     *
     * @return a string describing the action performed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return target.beCuredBy(item, actor, map);
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor The actor performing the action.
     * @return a String describing the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " uses the " + item + " to cure the " + target;
    }

}
