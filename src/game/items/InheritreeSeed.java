package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.items.Item;
import game.grounds.Inheritree;
import game.grounds.Soil;
import game.interfaces.Plantable;
import game.capabilities.Status;

/**
 * A class representing the {@link InheritreeSeed} item.
 * This seed can be planted to grow an Inheritree.
 */
public class InheritreeSeed extends Item implements Plantable {
    /**
     * Constructor.
     */
    public InheritreeSeed() {
        super("Inheritree Seed", '*', true);
    }

    /**
     * This method creates a list of actions that can be performed with the Inheritree Seed.
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
     * @return The stamina cost to plant the seed
     */
    @Override
    public int getStaminaCost() {
        return 25;
    }

    /**
     * This method returns the planted form of the seed, which is an Inheritree.
     *
     * @return The planted form of the seed
     */
    @Override
    public Ground getPlantedForm() {
        return new Inheritree();
    }

    /**
     * Plants the Inheritree at the specified location.
     *
     * @param tile The location where the seed is planted
     */
    @Override
    public void bloom(Location tile) {
        // Bloom the Inheritree here
        tile.setGround(getPlantedForm());

        // Cleanse cursed tiles around this location
        for (Exit exit : tile.getExits()) {
            Location adjacent = exit.getDestination();
            if (adjacent.getGround().hasCapability(Status.CURSED)) {
                adjacent.setGround(new Soil());  // Replace with soil
            }
        }
    }

    /**
     * This method is called when the seed is planted.
     * It changes the surrounding blight to soil and adds the Inheritree to the map.
     *
     * @param actor The actor who planted the seed
     * @param map   The game map where the seed is planted
     */
    @Override
    public void plant(Actor actor, GameMap map) {
        // Remove the seed from the actor's inventory
        actor.removeItemFromInventory(this);
        
        // Add the planted form of the seed to the current location
        Location actorLocation = map.locationOf(actor);
        bloom(actorLocation);

        // Deduct stamina from the actor
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, 25);
    }

}
