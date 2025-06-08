package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.WaterAction;

public abstract class Sprout extends Ground {
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     * @param name
     */
    public Sprout(char displayChar, String name) {
        super(displayChar, name);
    }

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

    abstract void grow(Location location);

    public String water() {
        return this + " has been watered";
    }
}
