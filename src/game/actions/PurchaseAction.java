package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.purchaseeffects.MerchantOffer;

/**
 * Represents an action to purchase an item from a Merchant.
 */
public class PurchaseAction extends Action {
    private final MerchantOffer offer;
    private final Actor merchant;
    private final Actor buyer;

    /**
     * Constructor for the PurchaseAction.
     *
     * @param offer The offer to be purchased
     * @param merchant The Merchant selling the offer
     * @param buyer The Actor that is buying the offer
     */
    public PurchaseAction(MerchantOffer offer, Actor merchant, Actor buyer) {
        this.offer = offer;
        this.merchant = merchant;
        this.buyer = buyer;
    }

    /**
     * Executes the purchase action, completing the purchase of the offer.
     *
     * @param actor The actor performing the action
     * @param map The game map where the action is performed
     * @return a string describing the result of the purchase
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return offer.completePurchase(buyer, map);
    }

    /**
     * Returns a description of the purchase action for the menu.
     *
     * @param actor The actor performing the action
     * @return a string description of the purchase action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " purchases a " + offer.getItem() + " from " + merchant;
    }

}
