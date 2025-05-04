package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Purchasable;

public class Katana extends WeaponItem implements Purchasable {

    /**
     * Constructor.
     */
    public Katana() {
        super("Katana", 'j', 50, "slices", 60);
    }
    
    @Override
    public void uponPurchase(Actor buyer, GameMap map) {
        buyer.hurt(25);
    }

}
