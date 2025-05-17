package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Merchant;
import game.purchaseeffects.MerchantOffer;

public class PurchaseAction extends Action {
    private MerchantOffer offer;
    private Merchant merchant;
    private Actor buyer;

    /**
     * Constructor.
     *
     * @param offer  The offer to be purchased
     * @param merchant The Merchant selling the offer
     * @param buyer The Actor that is buying the offer
     */
    public PurchaseAction(MerchantOffer offer, Merchant merchant, Actor buyer) {
        this.offer = offer;
        this.merchant = merchant;
        this.buyer = buyer;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return offer.completePurchase(buyer, map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " purchases a " + offer.getItem() + " from " + merchant;
    }

}
