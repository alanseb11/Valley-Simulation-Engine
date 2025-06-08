package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Status;
import game.items.plants.Plantable;
import game.utilities.Utility;

/**
 * An action that allows an actor to plant a seed in the game.
 */
public class PlantAction extends Action {
    /**
     * The seed to be planted
     */
    private Plantable seed;

    /**
     * Constructor.
     *
     * @param seed the {@Plantable} to plant
     */
    public PlantAction(Plantable seed) {
        this.seed = seed;
    }

    /**
     * When a seed is planted, remove the item from the actor's inventory and add it
     * to the current location of the actor.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a string describing who has planted which item.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Check if the actor has enough stamina to plant the seed
        if (!seed.isPlantableBy(actor)) {
            return actor + " does not have enough stamina to plant the " + seed;
        }

        // Add the planted form of the seed to the current location
        seed.plant(actor, map);

        // Display a message indicating the seed has been planted
        String crop = seed.getPlantedForm().toString();
        String article = Utility.startsWithVowel(crop) ? "an " : "a ";
        String msg = actor + " plants the " + seed + " and it blooms into " + article + crop;
        
        if (seed.getStaminaCost(actor) > 0 && actor.hasCapability(Status.BUFFED)) {
            return msg + ", spending less stamina than usual";
        }
        return actor + " plants the " + seed + " and it blooms into " + article + crop;
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor The actor performing the action.
     * @return a String describing the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " plants the " + seed;
    }

}
