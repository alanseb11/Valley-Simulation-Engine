package game.grounds.plants;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.WaterAction;
import game.capabilities.Status;

/**
 * An abstract class representing all Sprouts of crops.
 */
public abstract class Sprout extends Ground {
    /**
     * Constructor for Sprout.
     *
     * @param displayChar Character to display for this type of terrain
     * @param name The name of the sprout
     */
    public Sprout(char displayChar, String name) {
        super(displayChar, name);
        this.addCapability(Status.PLANTED);
    }

    /**
     * All Sprouts have WaterAction as part of their allowableActions list.
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return A list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        // Check if the actor is on the same location as the sprout
        if (location.getActor() == actor) {
            // Allows the actor to water the sprout
            actions.add((new WaterAction(this)));
        }

        return actions;
    }

    /**
     * An abstract method grow that executes when the sprout is fully grown.
     *
     * @param location The location of the Sprout
     */
    abstract void grow(Location location);

    /**
     * The water method for when the player waters the sprout.
     *
     * @return A string of the action.
     */
    public String water() {
        return this + " has been watered";
    }
}
