package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Status;

public class FollowBehaviour implements Behaviour {
    private Actor followTarget = null;

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location actorLocation = map.locationOf(actor);

        if (followTarget == null) {
            for (Exit exit : actorLocation.getExits()) {
                Actor nearby = exit.getDestination().getActor();
                if (nearby != null && nearby.hasCapability(Status.FOLLOWABLE)) {
                    followTarget = nearby;
                    break;
                }
            }
        }
        if (followTarget != null && map.contains(followTarget)) {
            Location targetLocation = map.locationOf(followTarget);

            for (Exit exit : actorLocation.getExits()) {
                if (exit.getDestination().equals(targetLocation)) {
                    return new MoveActorAction(targetLocation, exit.getName());
                }
            }

        } else{
            followTarget = null;
        }
        return null;
    }

}
