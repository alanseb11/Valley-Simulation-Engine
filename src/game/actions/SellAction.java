package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Sellable;

public class SellAction extends Action {
    private final Sellable sellable;
    private final Actor merchant;

    public SellAction(Sellable sellable, Actor merchant) {
        this.sellable = sellable;
        this.merchant = merchant;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return sellable.sell(actor, merchant);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " sells a " + sellable + " to " + merchant;
    }
}
