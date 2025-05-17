package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Purchasable;

/**
 * Represents the Dragonslayer Greatsword.
 * This weapon can be purchased and provides a health bonus to the buyer upon purchase.
 */
public class DragonslayerGreatsword extends WeaponItem implements Purchasable {

    /**
     * Constructor for the Dragonslayer Greatsword.
     * Initializes the weapon with a name, display character, damage, verb, and hit rate.
     */
    public DragonslayerGreatsword() {
        super("Dragonslayer Greatsword", 'D', 70, "strikes", 75);
    }

    /**
     * Applies the effects of purchasing the Dragonslayer Greatsword.
     * Increases the buyer's maximum health by 15.
     *
     * @param buyer the Actor who purchases the weapon
     * @param map the GameMap where the purchase takes place
     */
    @Override
    public void uponPurchase(Actor buyer, GameMap map) {
        buyer.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 15);
    }

}
