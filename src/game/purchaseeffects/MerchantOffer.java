package game.purchaseeffects;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.PurchaseEffect;
import game.interfaces.Merchant;
import game.interfaces.Purchasable;

/**
 * MerchantOffer is a class that represents an offer made by a merchant to a buyer.
 * It contains the item being sold, its price, and the effects that will be applied to the buyer upon purchase.
 */
public class MerchantOffer {
    private Merchant merchant;
    private final Purchasable item;
    private final int price;
    private final List<PurchaseEffect> effects;

    /**
     * Constructor.
     *
     * @param item        The item to be purchased
     * @param price       The price of the item
     * @param effects     The effect[s] to be applied to the buyer
     */
    public MerchantOffer(Merchant merchant, Purchasable item, int price, List<PurchaseEffect> effects) {
        this.merchant = merchant;
        this.item = item;
        this.price = price;
        this.effects = new ArrayList<>(effects); // Defensive copy
    }

    /**
     * Method to be called when an item is purchased.
     *
     * @param buyer The Actor that is buying the item
     * @param map   The map containing the Actor
     * @return A string describing the result of the purchase
     */
    public String completePurchase(Actor buyer, GameMap map) {
        // Check if the buyer has enough money
        if (buyer.getBalance() < price) {
            return merchant + " scoffs. \"The cost is beyond your means. Return when you have more runes.\"";
        }

        // Deduct the price from the buyer's money
        buyer.deductBalance(price);

        // Item-specific effect
        item.uponPurchase(buyer, map);

        // Add the item to the buyer's inventory
        buyer.addItemToInventory((Item) item);

        // Apply the single effect to the buyer
        if (effects.size() == 1) {
            return "\"Pleasure doing business,\" grins " + merchant + " as " + buyer + " receives the " + item + ".\nInstantly, " + effects.get(0).uponPurchase(buyer, map) + ".\n";
        }

        // If there are multiple effects, apply them all
        String effectString = "";
        for (int i = 0; i < effects.size() - 1; i++) {
            effectString += effects.get(i).uponPurchase(buyer, map) + ",\n";
        }
        // Add the last effect with "and"
        effectString += "and " + effects.get(effects.size() - 1).uponPurchase(buyer, map) + ".\n";

        return "\"Pleasure doing business,\" grins " + merchant + " as " + buyer + " receives the " + item + ".\nInstantly, " + effectString;
    }

    /**
     * Returns a string of the item.
     *
     * @return A string of the item.
     */
    public String getItem() {
        return item.toString();
    }

}
