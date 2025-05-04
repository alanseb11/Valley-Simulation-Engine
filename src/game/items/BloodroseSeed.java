package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Bloodrose;
import game.interfaces.Plantable;

/**
 * A class representing the Bloodrose Seed item.
 * This seed can be planted to grow a Bloodrose.
 */
public class BloodroseSeed extends Item implements Plantable {
    /**
     * Constructor.
     */
    public BloodroseSeed() {
        super("Bloodrose Seed", '*', true);
    }

    /**
     * This method creates a list of actions that can be performed with the Bloodrose Seed.
     *
     * @param actor The actor who wants to plant the seed
     * @return A list of actions that can be performed with the seed
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        return getPlantAction(owner, map, this);
	}

    /**
     * This method returns the stamina cost required to plant the seed.
     *
     * @return The stamina cost
     */
    @Override
    public int getStaminaCost() {
        return 75; // The stamina cost to plant the seed
    }

    /**
     * This method returns the planted form of the seed, which is a Bloodrose.
     *
     * @return The planted form of the seed
     */
    @Override
    public Ground getPlantedForm() {
        return new Bloodrose();
    }

    /**
     * Plants the Bloodrose at the specified location.
     *
     * @param tile The location where the seed is planted
     */
    @Override
    public void bloom(Location tile) {
        // Bloom the Bloodrose here
        tile.setGround(getPlantedForm());
    }

    /**
     * This method is called when the seed is planted.
     * It adds the Bloodrose to the map and saps the player's health.
     *
     * @param actor The actor who planted the seed
     * @param map   The game map where the seed is planted
     */
    @Override
    public void plant(Actor actor, GameMap map) {
        // Remove the seed from the actor's inventory
        actor.removeItemFromInventory(this);

        // Sprout where actor is
        Location actorLocation = map.locationOf(actor);
        bloom(actorLocation);

        // Sap the player's health once planted
        actor.hurt(5);
        
        // Deduct stamina from the actor
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, 75);
    }

}
