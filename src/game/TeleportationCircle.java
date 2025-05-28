package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.CureAction;
import game.actions.TeleportAction;
import game.capabilities.Status;
import java.util.*;

public class TeleportationCircle extends Ground {

    private Location location;
    private GameMap currentMap;
    private GameMap destinationMap;
    private Location destinationLocation;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     * @param name
     */
    public TeleportationCircle(char displayChar, String name) {
        super('A', "Teleportation Circle");
    }

    /**
     * Sets the location and map of this teleportation circle.
     * This should be called when the circle is placed on the map.
     *
     * @param location the location where this circle is placed
     */
    public void setLocation(Location location) {
        this.location = location;
        this.currentMap = location.map();
    }

    /**
     * Sets the destination for this teleportation circle.
     * This should be called when setting up the teleportation network.
     *
     * @param destinationMap the map to teleport to
     * @param destinationLocation the location on the destination map to teleport to
     */
    public void setDestination(GameMap destinationMap, Location destinationLocation) {
        this.destinationMap = destinationMap;
        this.destinationLocation = destinationLocation;
    }

    /**
     * Gets the location of this teleportation circle.
     *
     * @return the location of this circle
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Gets the map this teleportation circle is on.
     *
     * @return the map containing this circle
     */
    public GameMap getCurrentMap() {
        return this.currentMap;
    }

    /**
     * Gets the destination map for this teleportation circle.
     *
     * @return the map to teleport to
     */
    public GameMap getDestinationMap() {
        return this.destinationMap;
    }

    /**
     * Gets the destination location for this teleportation circle.
     *
     * @return the location to teleport to
     */
    public Location getDestinationLocation() {
        return this.destinationLocation;
    }

    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        // Check if the actor is on the same location as the teleport circle
        if (location.getActor() == actor && destinationMap != null && destinationLocation != null) {
            // Add teleport action
            actions.add(new TeleportAction(destinationLocation));
        }
        return actions;
    }

}
