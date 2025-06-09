package game.grounds.plants;

import edu.monash.fit2099.engine.positions.Location;
import game.time.Countdown;
import game.capabilities.Status;
import game.grounds.Soil;
import game.items.food.Hydrofruit;

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
        if (timeUntilGrown.isExpired()) {
            grow(location);
        } else {
            timeUntilGrown.applyTo(this, "growing into a Hydrofruit");
        }
    }

    @Override
    public void grow(Location location) {
        // Sets ground back to soil once it's fully grown
        location.setGround(new Soil());

        // A new Hydrofruit is added at the location once the sprout is grown
        location.addItem(new Hydrofruit());
        // If watered 3 or more times, then it produces twice the amount
        if (watered >= 3) {
            location.addItem(new Hydrofruit());
        }
    }

    @Override
    public String water() {
        watered ++;
        return super.water();
    }
}
