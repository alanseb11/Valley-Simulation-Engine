package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Eatable;

/**
 * Represents an action where an {@link Actor} eats an {@link Eatable}.
 * This action allows the actor to consume an edible.
 */
public class EatAction extends Action{
    private final Eatable edible;

    /**
     * Constructor.
     *
     * @param edible The thing that can be eaten.
     */
    public EatAction(Eatable edible){
        this.edible = edible;
    }

    /**
     * Executes the action of the edible being eaten by the actor.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string describing the action of the actor eating the edible.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return edible.eatenBy(actor, map);
    }

    /**
     * A menu description of the actor eating the edible.
     *
     * @param actor The actor performing the action.
     * @return A string of the menu description.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " eats the " + edible;
    }
}
