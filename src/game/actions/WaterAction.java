package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.plants.Sprout;

/**
 * Represents an action to water a Sprout.
 */
public class WaterAction extends Action {
    private final Sprout sprout;

    /**
     * Constructor for WaterAction.
     *
     * @param sprout The sprout to be watered
     */
    public WaterAction(Sprout sprout) {
        this.sprout = sprout;
    }

    /**
     * Executes the water action.
     *
     * @param actor The actor performing the action
     * @param map The game map where the action is performed
     * @return a string describing the result of watering the sprout
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return sprout.water();
    }

    /**
     * Returns a description of the water action for the menu.
     *
     * @param actor The actor performing the action
     * @return a string description of the water action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " waters the " + sprout;
    }
}
