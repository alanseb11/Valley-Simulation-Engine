package game.purchaseeffects;

import java.util.ArrayList;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.PurchaseEffect;
import game.interfaces.Merchant;
import game.interfaces.Purchasable;

public class MerchantOffer {
    private Merchant merchant;
    private final Purchasable item;
    private final int price;
    private final ArrayList<PurchaseEffect> effects;

    /**
     * Constructor.
     *
     * @param item        The item to be purchased
     * @param price       The price of the item
     * @param effect      The effect to be applied to the buyer
     */
    public MerchantOffer(Merchant merchant, Purchasable item, int price, ArrayList<PurchaseEffect> effects) {
        this.merchant = merchant;
        this.item = item;
        this.price = price;
        this.effects = effects;
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

        // Remove the item from the merchant's inventory? Or remove quantity?
        // Add the item to the buyer's inventory
        buyer.addItemToInventory((Item) item);

        String effectString = "";

        // Apply the effects to the buyer
        for (PurchaseEffect effect : effects) {
            effectString += effect.uponPurchase(buyer, map) + "\n";
        }
        return "\"Pleasure doing business,\" grins " + merchant + " as " + buyer + " receives the " + item + "\nInstantly, " + effectString;
    }

    public String getItem() {
        return item.toString();
    }

}
