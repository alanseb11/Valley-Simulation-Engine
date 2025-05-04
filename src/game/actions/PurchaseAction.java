package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Merchant;
import game.interfaces.Purchasable;

public class PurchaseAction extends Action {
    private Purchasable item;
    private Merchant merchant;
    private Actor buyer;

    /**
     * Constructor.
     *
     * @param item  The item to be purchased
     * @param merchant The Merchant selling the item
     * @param buyer The Actor that is buying the item
     */
    public PurchaseAction(Purchasable item, Merchant merchant, Actor buyer) {
        this.item = item;
        this.buyer = buyer;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        item.uponPurchase(buyer, map);
        return merchant.uponPurchase(buyer, map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " purchases a " + item + " from " + merchant;
    }

}
