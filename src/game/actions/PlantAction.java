package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Plantable;

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
     * @param item the item to plant
     */
    public PlantAction(Plantable seed) {
        this.seed = seed;
    }

    /**
     * When a seed is planted, remove the item from the actor's inventory and add it to the current location of the actor.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
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
        String crop = seed.getPlantedForm().toString();
        String article = startsWithVowel(crop) ? "an " : "a ";
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

    /**
     * Utility method to determine if a word starts with a vowel.
     *
     * @param word The word to check.
     * @return true if the word starts with a vowel, false otherwise.
     */
    private boolean startsWithVowel(String word) {
        char firstChar = Character.toLowerCase(word.charAt(0));
        return firstChar == 'a' || firstChar == 'e' || firstChar == 'i' || firstChar == 'o' || firstChar == 'u';
    }

}
