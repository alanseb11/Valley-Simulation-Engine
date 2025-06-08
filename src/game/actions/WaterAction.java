package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.Sprout;

public class WaterAction extends Action {
    private final Sprout sprout;

    public WaterAction(Sprout sprout) {
        this.sprout = sprout;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return sprout.water();
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " has watered the " + sprout;
    }
}
