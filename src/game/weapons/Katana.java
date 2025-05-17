package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Purchasable;

/**
 * Represents the Katana.
 * This weapon can be purchased but has a negative effect on the buyer's health upon purchase.
 */
public class Katana extends WeaponItem implements Purchasable {

    /**
     * Constructor for the Katana.
     * Initializes the weapon with a name, display character, damage, verb, and hit rate.
     */
    public Katana() {
        super("Katana", 'j', 50, "slices", 60);
    }
    
    /**
     * Applies the effects of purchasing the Katana.
     * Decreases the buyer's health by 25.
     *
     * @param buyer the Actor who purchases the weapon
     * @param map the GameMap where the purchase takes place
     */
    @Override
    public void uponPurchase(Actor buyer, GameMap map) {
        buyer.hurt(25);
    }

}
