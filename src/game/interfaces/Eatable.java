package game.interfaces;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.EatAction;
import game.capabilities.Status;

public interface Eatable {
    String eatenBy(Actor actor, GameMap map);

    // default ActionList getEatAction(Actor actor) {
    //     ActionList actions = new ActionList();
    //     if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
    //         actions.add(new EatAction(this));
    //     }
    //     return actions;
    // }
}
