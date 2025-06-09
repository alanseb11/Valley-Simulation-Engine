package game.items.plants;

import game.grounds.plants.HydrofruitSprout;

/**
 * A class representing the HydrofruitSeed item which is plantable.
 */
public class HydrofruitSeed extends Plantable{
    /**
     * Constructor for the HydrofruitSeed.
     */
    public HydrofruitSeed() {
        super("HydrofruitSeed", '*', true, new HydrofruitSprout(), 50);
    }
}
