package game.purchaseeffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.PurchaseEffect;

/**
 * IncreaseMaxEffect is a class that implements the PurchaseEffect interface.
 * It increases an actor's attribute maximum by a specific amount when an item is purchased.
 */
public class IncreaseMaxEffect implements PurchaseEffect {
    private final int increaseAmount;
    private BaseActorAttributes attribute;

    /**
     * Constructor.
     *
     * @param increaseAmount The amount to increase the maximum value by
     */
    public IncreaseMaxEffect(BaseActorAttributes attribute, int increaseAmount) {
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
        buyer.modifyAttributeMaximum(attribute, ActorAttributeOperations.INCREASE, increaseAmount);
        return buyer + "'s capacity for " + attribute.toString().toLowerCase() + " deepens";
    }

}
