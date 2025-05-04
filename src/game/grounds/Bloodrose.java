package game.grounds;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.Ground;
import game.capabilities.Status;

/**
 * A class representing the Bloodrose crop.
 */
public class Bloodrose extends Ground {

    /**
     * Constructor.
     */
    public Bloodrose() {
        super('w', "Bloodrose");
        this.addCapability(Status.PLANTED);
    }

    /**
     * This method is called once per turn if the item is resting on the ground.
     * 
     * The Bloodrose hurts all actors in its surrounding area by 10 hit points.
     * 
     * @param currentLocation The location of the ground on which the Bloodrose lies.
     */
    @Override
    public void tick(Location currentLocation) {
        for (Exit exit : currentLocation.getExits()) {
            Location adjacentTile = exit.getDestination();
            
            // Check if the surrounding area has an actor
            if (adjacentTile.containsAnActor()) {
                adjacentTile.getActor().hurt(10);
            }
        }
    }

}
