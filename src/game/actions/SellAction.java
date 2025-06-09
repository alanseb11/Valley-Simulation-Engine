package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Sellable;

/**
 * Represents an action to sell an item to a Merchant.
 */
public class SellAction extends Action {
    private final Sellable sellable;
    private final Actor merchant;

    /**
     * Constructor for SellAction.
     *
     * @param sellable The item to be sold
     * @param merchant The merchant buying the item
     */
    public SellAction(Sellable sellable, Actor merchant) {
        this.sellable = sellable;
        this.merchant = merchant;
    }

    /**
     * Executes the sell action, completing the sale of the item.
     *
     * @param actor The actor performing the action
     * @param map The game map where the action is performed
     * @return a string describing the result of the sale
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return sellable.sell(actor, merchant);
    }

    /**
     * Returns a description of the sell action for the menu.
     *
     * @param actor The actor performing the action
     * @return a string description of the sell action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " sells a " + sellable + " to " + merchant;
    }
}
