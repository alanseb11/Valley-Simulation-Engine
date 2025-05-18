package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Producible;

public class ProduceAction extends Action {
    private Producible producible;

    public ProduceAction(Producible producible) {
        this.producible = producible;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return producible.produce(actor, map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " has produced.";
    }
}
