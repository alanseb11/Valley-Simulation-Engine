package game.items.plants;

import game.grounds.plants.LumentreeSprout;

/**
 * A class representing the HydrofruitSeed item which is plantable.
 */
public class LumentreeSeed extends Plantable{
    /**
     * Constructor for the LumentreeSeed.
     */
    public LumentreeSeed() {
        super("LumentreeSeed", '*', true, new LumentreeSprout(), 50);
    }
}
