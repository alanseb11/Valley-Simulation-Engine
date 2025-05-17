package game.purchaseeffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.PurchaseEffect;

public class RestoreEffect implements PurchaseEffect {
    private final int increaseAmount;
    private BaseActorAttributes attribute;

    /**
     * Constructor.
     *
     * @param increaseAmount The amount to increase the maximum value by
     */
    public RestoreEffect(BaseActorAttributes attribute, int increaseAmount) {
        this.attribute = attribute;
        this.increaseAmount = increaseAmount;
    }

    /**
     * Method to be called when an item is purchased.
     *
     * @param buyer The Actor that is buying the item
     * @param map   The map containing the Actor
     * @return A string describing the result of the purchase
     */
    @Override
    public String uponPurchase(Actor buyer, GameMap map) {
        // Increase the maximum value of the buyer
        buyer.modifyAttribute(attribute, ActorAttributeOperations.INCREASE, increaseAmount);
        return "a surge of " + attribute.toString().toLowerCase() + " flows through " + buyer + "'s veins";
    }

}
