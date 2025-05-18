package game.monologueconditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Status;
import game.interfaces.MonologueCondition;

/**
 * A condition that checks if any of the surrounding entities have a specific capability.
 * This condition can be used to trigger monologues based on the capabilities of surrounding entities.
 */
public class SurroundingCapabilityCondition implements MonologueCondition {
    private Status status;

    /**
     * Constructor.
     */
    public SurroundingCapabilityCondition(Status status) {
        this.status = status;
    }

    /**
     * Checks if any of the surrounding enitities have the specified capability.
     *
     * @param listener The Actor to check
     * @param map      The map containing the Actor
     * @return true if any of the surrounding entities have the specified capability, false otherwise
     */
    @Override
    public boolean test(Actor listener, GameMap map) {
        return map.locationOf(listener).getExits().stream()
            .map(Exit::getDestination)
            .anyMatch(location -> hasCapabilityInLocation(location, status));
    }
    
    public static boolean hasCapabilityInLocation(Location location, Status status) {
        // Check if the ground has the specified capability
        if (location.getGround().hasCapability(status)) {
            return true;
        }
    
        // Check if an actor at the location has the specified capability
        Actor actor = location.getActor();
        if (actor != null && actor.hasCapability(status)) {
            return true;
        }
    
        // Check if any item at the location has the specified capability
        return location.getItems().stream().anyMatch(item -> item.hasCapability(status));
    }

}
