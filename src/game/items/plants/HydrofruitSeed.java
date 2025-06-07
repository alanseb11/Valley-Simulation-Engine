package game.items.plants;

import edu.monash.fit2099.engine.positions.Ground;
import game.grounds.HydrofruitSprout;

public class HydrofruitSeed extends Plantable{
    /**
     * Constructor.
     */
    public HydrofruitSeed() {
        super("HydrofruitSeed", '*', true, new HydrofruitSprout(), 50);
    }
}
