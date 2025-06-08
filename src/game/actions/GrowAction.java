package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Growable;

public class GrowAction extends Action {
    private final Growable growable;

    public GrowAction(Growable growable) {
        this.growable = growable;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return growable.grow();
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " grows a new branch or leaf";
    }
}
