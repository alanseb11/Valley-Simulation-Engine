package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class TeleportAction extends Action {
    
    private final Location destination;

    public TeleportAction(Location destination) {
        this.destination = destination;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // Remove actor from current map
        map.removeActor(actor);
        
        // Add actor to the destination map at the specified location
        destination.map().addActor(actor, destination);
        
        return actor + " teleports to " + destination.map().toString();
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " teleports to " + destination.map().toString();
    }
}
