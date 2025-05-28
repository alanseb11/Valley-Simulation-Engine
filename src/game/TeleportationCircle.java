package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.CureAction;
import game.capabilities.Status;

public class TeleportationCircle extends Ground {

    GameMap destinationMap;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     * @param name
     */
    public TeleportationCircle(char displayChar, String name) {
        super('A', "Teleportation Circle");
    }


    public ActionList allowableActions(Actor actor, Location location) {
        ActionList actions = new ActionList();

        // Check if the actor is on the same location as the teleport circle
        if (location.getActor() == actor) {
            // Add teleport action
        }

        return actions;
    }

}
