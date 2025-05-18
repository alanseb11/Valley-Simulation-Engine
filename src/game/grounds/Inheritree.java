package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.Ground;
import game.capabilities.Status;

/**
 * A class representing the Inheritree crop.
 * 
 * The Inheritree is a unique item that provides a healing effect to actors
 * in adjacent locations when it is on the ground. Each turn, it heals all
 * actors in the surrounding area by a fixed amount.
 */
public class Inheritree extends Ground {

    /**
     * Constructor.
     */
    public Inheritree() {
        super('t', "Inheritree");
        this.addCapability(Status.PLANTED);
        this.addCapability(Status.BLESSED_BY_GRACE);
    }

    /**
     * This method is called once per turn if the item is resting on the ground.
     * 
     * The Inheritree heals all actors in its surrounding area by 5 hit points.
     * 
     * @param currentLocation The location of the ground on which the Inheritree lies.
     */
    @Override
    public void tick(Location currentLocation) {
        for (Exit exit : currentLocation.getExits()) {
            Location adjacentTile = exit.getDestination();
            
            // Check if the surrounding area has an actor
            if (adjacentTile.containsAnActor()) {
                // Heal the actor in the adjacent Tile
                Actor actor = adjacentTile.getActor();
                actor.heal(5);
                
                // If the actor has stamina, restore it as well
                if (actor.hasAttribute(BaseActorAttributes.STAMINA)) {
                    actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, 5);
                }
            }
        }
    }

}
