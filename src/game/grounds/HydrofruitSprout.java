package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Countdown;
import game.capabilities.Status;

public class HydrofruitSprout extends Sprout {
    private final Countdown timeUntilGrown = new Countdown(7);
    private int watered = 0;

    /**
     * Constructor.
     */
    public HydrofruitSprout() {
        super('h', "HydrofruitSprout");
        this.addCapability(Status.PLANTED);
    }

    @Override
    public void tick(Location location) {
        if (timeUntilGrown.isExpired() && !location.containsAnActor()) {
            grow(location);
        } else {
            timeUntilGrown.applyTo(this, "growing into a Hydrofruit");
        }
    }

    @Override
    public void grow(Location location) {
        return;
    }

    @Override
    public String water() {
        watered ++;
        return super.water();
    }
}
