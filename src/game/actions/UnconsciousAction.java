package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Represents an action that handles an actor becoming unconscious.
 * 
 * This action is executed when an actor's health or countdown timer drops to a critical level,
 * rendering them unconscious. The action triggers the appropriate behavior for the actor
 * in this state, such as removing them from the map or applying specific effects.
 */ 
public class UnconsciousAction extends Action{
    
	/**
     * Executes the unconscious behaviour for the actor.
     * 
     * This method calls the {@link Actor#unconscious(GameMap)} method to handle
     * the actor's behaviour when they become unconscious.
     * 
     * @param actor The actor becoming unconscious
     * @param map   The game map where the action takes place
     * @return A string describing the result of the unconscious action
     */
	@Override
	public String execute(Actor actor, GameMap map) {
		return actor.unconscious(map);
	}

	/**
     * Returns a description of the unconscious action for the menu.
     * 
     * @param actor The actor performing the action
     * @return A string describing the action for the menu (currently returns {@code null})
     */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " is unconscious";
	}
}
