package game.items.plants;

import edu.monash.fit2099.engine.positions.Ground;
import game.grounds.LumentreeSprout;

public class LumentreeSeed extends Plantable{
    /**
     * Constructor.
     */
    public LumentreeSeed() {
        super("LumentreeSeed", '*', true, new LumentreeSprout(), 50);
    }
}
