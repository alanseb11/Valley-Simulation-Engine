package game.purchaseeffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.Exit;
import game.interfaces.PurchaseEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpawnEffect implements PurchaseEffect {
    private Actor actorToSpawn;

    /**
     * Constructor.
     * @param actorToSpawn The actor to be spawned.
     */
    public SpawnEffect(Actor actorToSpawn) {
        this.actorToSpawn = actorToSpawn;
    }

    /**
     * Spawns the actorToSpawn on a random available adjacent tile to the anchorActor.
     * @param buyer The actor making the purchase.
     * @param map The GameMap where spawning occurs.
     * @return A message describing the spawn, or an error if no location is found.
     */
    @Override
    public String uponPurchase(Actor buyer, GameMap map) {
        List<Location> possibleLocations = new ArrayList<>();

        // Check all adjacent exits from the buyer's location
        for (Exit exit : map.locationOf(buyer).getExits()) {
            Location destination = exit.getDestination();
            // Check if destination is valid
            if (destination.canActorEnter(actorToSpawn)) {
                possibleLocations.add(destination);
            }
        }

        if (possibleLocations.isEmpty()) {
            return "No available adjacent spawn location found near " + buyer + " for " + actorToSpawn + ".";
        }

        // Randomly select a location from the possible locations to spawn
        Collections.shuffle(possibleLocations);
        Location spawnLocation = possibleLocations.get(0);
        map.addActor(actorToSpawn, spawnLocation);
        
        return actorToSpawn + " spawns near " + buyer;
    }
}
