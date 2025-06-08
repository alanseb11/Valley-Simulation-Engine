package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Status;

public class LumentreeSprout extends Sprout {
    /**
     * Constructor.
     */
    public LumentreeSprout() {
        super('l', "LumentreeSprout");
        this.addCapability(Status.PLANTED);
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
    }

    @Override
    public void grow(Location location) {
        return;
    }

}
