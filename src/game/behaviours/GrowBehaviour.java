package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.GrowAction;
import game.capabilities.Status;
import game.interfaces.Growable;

public class GrowBehaviour implements Behaviour {
    private final Growable growable;

    public GrowBehaviour(Growable growable) {
        this.growable = growable;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (growable.canGrow(map)) {
            return new GrowAction(growable);
        }
        return null;
    }

}
