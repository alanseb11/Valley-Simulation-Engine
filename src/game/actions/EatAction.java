package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Eatable;

public class EatAction extends Action{

    public final Eatable edible;

    public EatAction(Eatable edible){
        this.edible = edible;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return edible.eatenBy(actor, map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " eats the " + edible;
    }
}
