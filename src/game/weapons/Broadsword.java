package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Purchasable;

public class Broadsword extends WeaponItem implements Purchasable {

    /**
     * Constructor.
     */
    public Broadsword() {
        super("Broadsword", 'b', 30, "slashes", 50);
    }

    @Override
    public void uponPurchase(Actor buyer, GameMap map) {
        buyer.heal(10);
    }

}
